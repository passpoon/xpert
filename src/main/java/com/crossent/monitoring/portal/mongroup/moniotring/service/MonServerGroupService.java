package com.crossent.monitoring.portal.mongroup.moniotring.service;

import com.crossent.monitoring.portal.common.constants.Constants;
import com.crossent.monitoring.portal.common.constants.StatusEnum;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.elasticsearch.ElasticsearchTemplate;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.CriticalValueMapVo;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.mongroup.moniotring.dao.MonServerDao;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.MeasurementStatusDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ProcessStatusDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerGroupStatusesResDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerStatusesResDto;
import com.crossent.monitoring.portal.mongroup.moniotring.util.MonitoringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private ServerResourceRepository serverResourceRepository;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private EventHistoryRepository eventHistoryRepository;



    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

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


            }

            pageServerGroupStatusesResDto.addListItem(serverGroupStatusesResDto);
        }

//        //모니터링 대상서버 목록
//        for (MgServer mgServer : mgServers) {
//            String hostName = mgServer.getServerResource().getHostName();
//            ServerStatusesResDto serverStatusesResDto = new ServerStatusesResDto();
//            serverStatusesResDto.setServerResourceId(mgServer.getServerResourceId());
//            serverStatusesResDto.setServerResourceName(mgServer.getServerResource().getName());
//            serverStatusesResDto.setHostName(hostName);
//
//
//
//            logger.debug("mgServer {} ::", mgServers);
//
//
//
//            for (MgServerTitleMap map : mgServerTitleMaps) {
//
//                StringBuilder contentBuffer = new StringBuilder();
//                Integer mId = map.getMeasurementId();
//                String mName = map.getMeasurements().getName();
//
//                //title입력
//                //serverStatusesResDto.addTile(mName);
//
//                Collection<MgServerCriticalValue> criticalValues = mgServerCriticalValueRepository
//                                                                           .findAllByMonGroupIdAndServerResourceIdAndMetric_MeasurementId(monitoringGroupId,
//                                                                                   mgServer.getServerResourceId(), mId);
//
//
//                if(logger.isDebugEnabled()){
//                    logger.debug("criticalValues size : {}", criticalValues.size());
//                    logger.debug("criticalValues  : {}", criticalValues);
//                }
//
//
//                StatusEnum status = StatusEnum.NA;
//
//                if(criticalValues != null && criticalValues.size() != 0){
//                    List<CriticalValueInterface> metriIfs = MonitoringUtil.criticalCollectionToInterface(criticalValues);
//
//                    CriticalValueMapVo cvMapDto = new CriticalValueMapVo(metriIfs);
//
//                    logger.debug("cvMapDto : {}", cvMapDto);
//
//
//                    Map<String, Object> referenceValueMap = monServerDao.selectReferenceValue(mName, hostName, cvMapDto);
//
//                    if(referenceValueMap != null){
//
//
//
//
//                        logger.debug("referenceValueMap : {}", referenceValueMap);
//                        List<String> metricNames = cvMapDto.getMetricNames();
//                        for(String metricName : metricNames) {
//
//                            Object val = referenceValueMap.get(metricName);
//
//                            if (val == null) {
//                                contentBuffer.append(MessageUtil.getMessage("statusNoRcvData", metricName) + "\n");
//                                status = status.max(StatusEnum.Error);
//                            } else {
//                                Double dVal = MonitoringUtil.toDouble(val);
//                                Double criticalVal = cvMapDto.getCriticalVal(metricName);
//                                Double warningVal = cvMapDto.getWarningVal(metricName);
//
//
//                                if (cvMapDto.isCritical(metricName, dVal)) {
//                                    status = status.max(StatusEnum.Critical);
//                                    contentBuffer.append(MessageUtil.getMessage("statusCritical", metricName, MonitoringUtil.round2ToString(dVal), MonitoringUtil.round2ToString(criticalVal)) + "\n");
//                                } else if (cvMapDto.isWarning(metricName, dVal)) {
//                                    status = status.max(StatusEnum.Warning);
//                                    contentBuffer.append(MessageUtil.getMessage("statusWarning", metricName, MonitoringUtil.round2ToString(dVal), MonitoringUtil.round2ToString(warningVal)) + "\n");
//                                } else {
//                                    status = status.max(StatusEnum.Nomal);
//                                    contentBuffer.append(MessageUtil.getMessage("statusNormal", metricName, MonitoringUtil.round2ToString(dVal)) + "\n");
//                                }
//                            }
//                        }
//                    }else{
//                        status = StatusEnum.Error;
//                        contentBuffer.append(MessageUtil.getMessage("failMetricInfo", mName));
//
//                    }
//                }else{
//                    //status = StatusEnum.NA;
//                    contentBuffer.append(MessageUtil.getMessage("unDefCriticalVal")+"\n"); //정의된 임계치가 없습니다.
//                }
//
//                MeasurementStatusDto mentStatusDto = new MeasurementStatusDto();
//                mentStatusDto.setMeasurementId(mId);
//                mentStatusDto.setContent(contentBuffer.toString());
//                mentStatusDto.setStatus(status.getString());
//                mentStatusDto.setMeasurementName(mName);
//
//                serverStatusesResDto.addMeasurementStatus(mentStatusDto);
//
//
//
//            }
//
//
//
//
//            //process정보 조회
//            StatusEnum procStatus = StatusEnum.Nomal;
//            StringBuilder procContent = new StringBuilder();
//            ProcessStatusDto processStatusDto  = new ProcessStatusDto();
//
//            Map<String, Object> processInfoForServer = monServerDao.selectProcessInfoForServer(hostName);
//
//            if(processInfoForServer == null){
//                procStatus = StatusEnum.Error;
//
//                processStatusDto.setTotalCnt(0L);
//                processStatusDto.setNormalCnt(0L);
//                processStatusDto.setAbnormalCnt(0L);
//
//                procContent.append(MessageUtil.getMessage("failProcessInfo", hostName));
//
//            }else{
//                Long normalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_NORMAL)));
//                Long abnormalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_ABNORMAL)));
//                Long totalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_TOTAL)));
//
//                if(abnormalCnt > 0){
//                    procStatus = StatusEnum.Error;
//                }
//                processStatusDto.setTotalCnt(totalCnt);
//                processStatusDto.setNormalCnt(normalCnt);
//                processStatusDto.setAbnormalCnt(abnormalCnt);
//
//                for(String keys : Constants.PROC_STAT_KEYS){
//                    procContent.append(keys + " : " + processInfoForServer.get(keys)+"\n");
//                }
//
//
//            }
//
//            processStatusDto.setStatus(procStatus.getString());
//            processStatusDto.setContent(procContent.toString());
//
//            serverStatusesResDto.setProcessStatus(processStatusDto);
//
//            pageServerStatusesResDtoPagingResVo.addListItem(serverStatusesResDto);
//
//
//
//
//        }
//
//
//        return pageServerStatusesResDtoPagingResVo;
        return null;
    }
}
