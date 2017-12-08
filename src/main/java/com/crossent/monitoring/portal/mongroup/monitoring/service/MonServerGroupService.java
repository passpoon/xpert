package com.crossent.monitoring.portal.mongroup.monitoring.service;

import com.crossent.monitoring.portal.common.constants.Constants;
import com.crossent.monitoring.portal.common.constants.ResourceType;
import com.crossent.monitoring.portal.common.constants.StatusEnum;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.CriticalValueMapVo;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.mongroup.monitoring.dao.MonServerDao;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.*;
import com.crossent.monitoring.portal.mongroup.monitoring.util.MonitoringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonServerGroupService {

    private static Logger logger = LoggerFactory.getLogger(MonServerGroupService.class);


    @Autowired
    MgServerRepository mgServerRepository;


    @Autowired
    MgServerGroupRepository mgServerGroupRepository;

    @Autowired
    MgServerGroupTitleMapRepository mgServerGroupTitleMapRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MgServerGroupCriticalValueRepository mgServerGroupCriticalValueRepository;


    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MonServerDao monServerDao;


    @Autowired
    private MgServerTitleMapRepository mgServerTitleMapRepository;

    @Autowired
    private MonCommonService monCommonService;




    @Autowired
    private MgServerGroupServerRepository mgServerGroupServerRepository;


    public PagingResVo<ServerGroupStatusesResDto> pageServerGroupStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search) {

        String key = null;
        String keyword = null;
        Map<String, Map<String, String>> influxQueryFilters = applicationProperties.getInfluxQueryFilters();

        Map<String, String> keywords = search.getKeywords();
        if (keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while(keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                logger.debug("keyword ::::: " + keyword);
                keyword = keyword;
            }
        }


       // Page<MgServerGroup> pageMgServerGroup = null;

        logger.debug("key : {}", key);
        logger.debug("keyword : {}", keyword);


        Page<MgServerGroup> pageMgServerGroup = null;

        if(key == null){
            pageMgServerGroup = mgServerGroupRepository.findAllByMonGroupIdAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId, "Y");
        }else{
            switch (key) {
                case "serverGroupName": {
                    pageMgServerGroup = mgServerGroupRepository.findAllByMonGroupIdAndNameContainsAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId,  keyword, "Y");
                }
                break;
                default:
                    throw new BusinessException("unDefSearchKey", key);

            }
        }

        PagingResVo<ServerGroupStatusesResDto> pageServerGroupStatusesResDto = new PagingResVo<ServerGroupStatusesResDto>(pageMgServerGroup, false);

        Collection<MgServerGroupTitleMap> mgServerGroupTitleMaps= mgServerGroupTitleMapRepository.findAllByMonGroupId(monitoringGroupId);
        for(MgServerGroupTitleMap mgServerGroupTitleMap : mgServerGroupTitleMaps){
            pageServerGroupStatusesResDto.addTile(mgServerGroupTitleMap.getMeasurement().getName());
        }

        List<MgServerGroup> content = pageMgServerGroup.getContent();
        for(MgServerGroup mgServerGroup : content){
            Integer mgServerGroupId = mgServerGroup.getId();
            String mgServerGroupName = mgServerGroup.getName();
            String mgServerGroupDescription = mgServerGroup.getDescription();
            ServerGroupStatusesResDto serverGroupStatusesResDto = new ServerGroupStatusesResDto();
            serverGroupStatusesResDto.setServerGroupId(mgServerGroupId);
            serverGroupStatusesResDto.setServerGroupName(mgServerGroupName);
            serverGroupStatusesResDto.setDescription(mgServerGroupDescription);

            ServerType serverType = mgServerGroup.getServerType();
            Collection<MgServer> mgServers = mgServerGroup.getMgServers();
            //Collection<MgServerGroupCriticalValue> mgServerGroupCriticalValues = mgServerGroup.getMgServerGroupCriticalValues();

            for(MgServerGroupTitleMap mgServerGroupTitleMap : mgServerGroupTitleMaps) {
                Integer measurementId = mgServerGroupTitleMap.getMeasurement().getId();
                String measurementName = mgServerGroupTitleMap.getMeasurement().getName();
                StringBuilder contentBuffer = new StringBuilder();
                Collection<MgServerGroupCriticalValue> mgServerGroupCriticalValues = mgServerGroupCriticalValueRepository.findAllByServerGroupIdAndMetric_MeasurementId(mgServerGroupId, measurementId);

                if(logger.isDebugEnabled()){
                    logger.debug("criticalValues size : {}", mgServerGroupCriticalValues.size());
                    logger.debug("criticalValues  : {}", mgServerGroupCriticalValues);
                }

                StatusEnum status = StatusEnum.NA;

                List<CriticalValueInterface> metriIfs = MonitoringUtil.criticalCollectionToInterface(mgServerGroupCriticalValues);
                CriticalValueMapVo cvMapDto = new CriticalValueMapVo(metriIfs);
                logger.debug("cvMapDto : {}", cvMapDto);

                if(mgServerGroupCriticalValues != null && mgServerGroupCriticalValues.size() != 0){
                    for (MgServer mgServer : mgServers) {
                        ServerResource serverResource = mgServer.getServerResource();
                        String hostName = serverResource.getHostName();
                        String serverResourceName = serverResource.getName();
                        Integer serverResourceId = serverResource.getId();
                        String serverResourceUuid = serverResource.getUuid();
                        contentBuffer.append("\n@" + serverResourceName + "(" + hostName + ")\n");

                        Map<String, Object> referenceValueMap = monServerDao.selectReferenceValue(measurementName, hostName, cvMapDto);
                        if(referenceValueMap != null){
                            logger.debug("referenceValueMap : {}", referenceValueMap);
                            List<String> metricNames = cvMapDto.getMetricNames();
                            for(String metricName : metricNames) {

                                Object val = referenceValueMap.get(metricName);

                                if (val == null) {
                                    contentBuffer.append(MessageUtil.getMessage("statusNoRcvData", metricName) + "\n");
                                    status = status.max(StatusEnum.Error);
                                } else {
                                    Double dVal = MonitoringUtil.toDouble(val);
                                    Double criticalVal = cvMapDto.getCriticalVal(metricName);
                                    Double warningVal = cvMapDto.getWarningVal(metricName);


                                    if (cvMapDto.isCritical(metricName, dVal)) {
                                        status = status.max(StatusEnum.Critical);
                                        contentBuffer.append(MessageUtil.getMessage("statusCritical", metricName, MonitoringUtil.round2ToString(dVal), MonitoringUtil.round2ToString(criticalVal)) + "\n");
                                    } else if (cvMapDto.isWarning(metricName, dVal)) {
                                        status = status.max(StatusEnum.Warning);
                                        contentBuffer.append(MessageUtil.getMessage("statusWarning", metricName, MonitoringUtil.round2ToString(dVal), MonitoringUtil.round2ToString(warningVal)) + "\n");
                                    } else {
                                        status = status.max(StatusEnum.Nomal);
                                        contentBuffer.append(MessageUtil.getMessage("statusNormal", metricName, MonitoringUtil.round2ToString(dVal)) + "\n");
                                    }
                                }
                            }
                        }else{
                            status = StatusEnum.Error;
                            contentBuffer.append(MessageUtil.getMessage("failMetricInfo", measurementName));

                        }
                    }




                }else {
                    contentBuffer.append(MessageUtil.getMessage("unDefCriticalVal")+"\n"); //정의된 임계치가 없습니다.
                }

                MeasurementStatusDto mentStatusDto = new MeasurementStatusDto();
                mentStatusDto.setMeasurementId(measurementId);
                mentStatusDto.setContent(contentBuffer.toString());
                mentStatusDto.setStatus(status.getString());
                mentStatusDto.setMeasurementName(measurementName);

                serverGroupStatusesResDto.addMeasurementStatus(mentStatusDto);


            }



            //process정보 조회
            StatusEnum procStatus = StatusEnum.Nomal;
            StringBuilder procContent = new StringBuilder();

            ProcessStatusDto processStatusDto  = new ProcessStatusDto();
            processStatusDto.setTotalCnt(0L);
            processStatusDto.setNormalCnt(0L);
            processStatusDto.setAbnormalCnt(0L);

            for(MgServer mgServer : mgServers) {
                String hostName = mgServer.getServerResource().getHostName();
                String serverResourceName = mgServer.getServerResource().getName();

                procContent.append("\n@" + serverResourceName + "(" + hostName + ")\n");

                Map<String, Object> processInfoForServer = monServerDao.selectProcessInfoForServer(hostName);

                if (processInfoForServer == null) {
                    procStatus = StatusEnum.Error;
                    procContent.append(MessageUtil.getMessage("failProcessInfo", hostName));

                } else {
                    Long normalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_NORMAL)));
                    Long abnormalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_ABNORMAL)));
                    Long totalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_TOTAL)));



                    procContent.append(MessageUtil.getMessage("processStatusTotal", totalCnt+"") + "\n");
                    procContent.append(MessageUtil.getMessage("processStatusNormal", normalCnt+"") + "\n");
                    procContent.append(MessageUtil.getMessage("processStatusAbnormal", normalCnt+"") + "\n");

                    if (abnormalCnt > 0) {
                        procStatus = StatusEnum.Error;
                    }
                    processStatusDto.setTotalCnt(processStatusDto.getTotalCnt() + totalCnt);
                    processStatusDto.setNormalCnt(processStatusDto.getNormalCnt() + normalCnt);
                    processStatusDto.setAbnormalCnt(processStatusDto.getAbnormalCnt() + abnormalCnt);


                    for (String keys : Constants.PROC_STAT_KEYS) {
                        procContent.append(keys + " : " + processInfoForServer.get(keys) + "\n");
                    }


                }
            }

            processStatusDto.setStatus(procStatus.getString());
            processStatusDto.setContent(procContent.toString());

            serverGroupStatusesResDto.setProcessStatus(processStatusDto);

            pageServerGroupStatusesResDto.addListItem(serverGroupStatusesResDto);

        }

        return pageServerGroupStatusesResDto;
        //return null;
    }


    public PagingResVo<ServerStatusesResDto> pageServerGroupServerStatuses(Integer monitoringGroupId, Integer serverGroupId, PagingReqVo paging) {
        String key = null;
        String keyword = null;
        Map<String, Map<String, String>> influxQueryFilters = applicationProperties.getInfluxQueryFilters();


        Page<MgServerGroupServer> pageMgServerGroupServers = mgServerGroupServerRepository.findAllByMonGroupIdAndServerGroupId(paging.toPagingRequest(), monitoringGroupId, serverGroupId);


        PagingResVo<ServerStatusesResDto> pageServerStatusesResDtoPagingResVo = new PagingResVo<ServerStatusesResDto>(pageMgServerGroupServers, false);

        List<MgServerGroupServer> serverGroupServers = pageMgServerGroupServers.getContent();



        List<ServerResource> serverResources = new ArrayList<ServerResource>();

        for(MgServerGroupServer mgServerGroupServer : serverGroupServers){
            serverResources.add(mgServerGroupServer.getServerResource());
        }

//        if (logger.isDebugEnabled()) {
//            logger.debug("mgServers : {}", mgServers);
//        }

        //logger.debug("mgServer {} ::", mgServers);
        Collection<MgServerTitleMap> mgServerTitleMaps = mgServerTitleMapRepository.findAllByMonGroupId(monitoringGroupId);

        //title셋팅
        for(MgServerTitleMap titleMap : mgServerTitleMaps){
            pageServerStatusesResDtoPagingResVo.addTile(titleMap.getMeasurements().getName());
        }

        List<ServerStatusesResDto> serverStatusesResDtos = monCommonService.pageServerStatuses(monitoringGroupId, mgServerTitleMaps, serverResources);
        pageServerStatusesResDtoPagingResVo.setList(serverStatusesResDtos);

        return pageServerStatusesResDtoPagingResVo;
    }


    public PagingResVo<EventResDto> pageEvent(Integer monitoringGroupId, Integer serverGroupId, PagingReqVo paging, SearchReqVo search){
        Collection<MgServerGroupServer> mgServerGroupServers = mgServerGroupServerRepository.findAllByMonGroupIdAndServerGroupId(monitoringGroupId, serverGroupId);

        List<Integer> resourceIds = new ArrayList<Integer>();

        for(MgServerGroupServer mgServerGroupServer : mgServerGroupServers){
            resourceIds.add(mgServerGroupServer.getServerResourceId());
        }

        PagingResVo<EventResDto> pagingResVo = monCommonService.pageEvent(monitoringGroupId, ResourceType.SERVER_GROUP, resourceIds, paging, search);

        return pagingResVo;
    }


    public PagingResVo<LogResDto> pageLog(Integer monitoringGroupId, Integer serverGroupId, PagingReqVo paging, SearchReqVo search){
        Collection<MgServerGroupServer> mgServerGroupServers = mgServerGroupServerRepository.findAllByMonGroupIdAndServerGroupId(monitoringGroupId, serverGroupId);

        List<Integer> resourceIds = new ArrayList<Integer>();

        for(MgServerGroupServer mgServerGroupServer : mgServerGroupServers){
            resourceIds.add(mgServerGroupServer.getServerResourceId());
        }

        PagingResVo<LogResDto> pagingResVo = monCommonService.pageServerLog(resourceIds, paging, search);

        return pagingResVo;
    }






}
