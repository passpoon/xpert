package com.crossent.monitoring.portal.mongroup.monitoring.service;

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
public class MonApplicationGroupService {

    private static Logger logger = LoggerFactory.getLogger(MonApplicationGroupService.class);


    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgAppRepository mgAppRepository;



//    @Autowired
//    MgServerGroupRepository mgServerGroupRepository;

    @Autowired
    MgAppGroupRepository mgAppGroupRepository;

//    @Autowired
//    MgServerGroupTitleMapRepository mgServerGroupTitleMapRepository;

    @Autowired
    MeasurementRepository measurementRepository;

//    @Autowired
//    MgServerGroupCriticalValueRepository mgServerGroupCriticalValueRepository;

    @Autowired
    MgAppGroupCriticalValueRepository mgAppGroupCriticalValueRepository;


    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MonServerDao monServerDao;


//    @Autowired
//    private MgServerTitleMapRepository mgServerTitleMapRepository;

    @Autowired
    private MonCommonService monCommonService;




//    @Autowired
//    private MgServerGroupServerRepository mgServerGroupServerRepository;

    @Autowired
    private MgAppGroupAppRepository mgAppGroupAppRepository;


    public PagingResVo<AppGroupStatusesResDto> pageAppGroupStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search) {

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


        Page<MgAppGroup> pageMgServerGroup = null;

        if(key == null){
            pageMgServerGroup = mgAppGroupRepository.findAllByMonGroupIdAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId, "Y");
        }else{
            switch (key) {
                case "serverGroupName": {
                    pageMgServerGroup = mgAppGroupRepository.findAllByMonGroupIdAndNameContainsAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId,  keyword, "Y");
                }
                break;
                default:
                    throw new BusinessException("unDefSearchKey", key);

            }
        }

        PagingResVo<AppGroupStatusesResDto> pageAppGroupStatusesResDto = new PagingResVo<AppGroupStatusesResDto>(pageMgServerGroup, false);

//        Collection<MgServerGroupTitleMap> mgServerGroupTitleMaps= mgServerGroupTitleMapRepository.findAllByMonGroupId(monitoringGroupId);
//        for(MgServerGroupTitleMap mgServerGroupTitleMap : mgServerGroupTitleMaps){
//            pageAppGroupStatusesResDto.addTitle(mgServerGroupTitleMap.getMeasurement().getName());
//        }

        List<MgAppGroup> content = pageMgServerGroup.getContent();

        for(MgAppGroup mgAppGroup : content){
            Integer mgAppGroupId = mgAppGroup.getId();
            String mgAppGroupName = mgAppGroup.getName();
            String mgAppGroupDescription = mgAppGroup.getDescription();
            AppGroupStatusesResDto appGroupStatusesResDto = new AppGroupStatusesResDto();
            appGroupStatusesResDto.setAppGroupId(mgAppGroupId);
            appGroupStatusesResDto.setAppGroupName(mgAppGroupName);
            appGroupStatusesResDto.setDescription(mgAppGroupDescription);

            AppInfo appInfo = mgAppGroup.getAppInfo();

            Collection<MgApp> mgApps = mgAppGroup.getMgApps();

            //Collection<MgServerGroupCriticalValue> mgServerGroupCriticalValues = mgServerGroup.getMgServerGroupCriticalValues();

            List<MgAppGroupCriticalValue> mgAppGroupCriticalValues = mgAppGroupCriticalValueRepository.findAllByMgAppGroupId(mgAppGroupId);

            List<CriticalValueInterface> criticalValueInterfaces = MonitoringUtil.criticalCollectionToInterface(mgAppGroupCriticalValues);

            Map<Integer, CriticalValueMapVo> measurementMap = MonitoringUtil.convertCriticalValToMeasurementMap(criticalValueInterfaces);

            Iterator<Integer> measurementIds = measurementMap.keySet().iterator();

            while(measurementIds.hasNext()) {

                Integer measurementId = measurementIds.next();
                Measurement measurement = measurementRepository.findOne(measurementId);
                String measurementName = measurement.getName();
                appGroupStatusesResDto.addTitle(measurementName);
                StringBuilder contentBuffer = new StringBuilder();


                if(logger.isDebugEnabled()){
                    logger.debug("criticalValues size : {}", mgAppGroupCriticalValues.size());
                    logger.debug("criticalValues  : {}", mgAppGroupCriticalValues);
                }

                StatusEnum status = StatusEnum.NA;

                //List<CriticalValueInterface> metriIfs = MonitoringUtil.criticalCollectionToInterface(mgServerGroupCriticalValues);

                CriticalValueMapVo cvMapDto = measurementMap.get(measurementId);
                logger.debug("cvMapDto : {}", cvMapDto);

                //if(cvMapDto != null) {

                for (MgApp mgApp : mgApps) {
                    AppResource appResource = mgApp.getAppResource();

                    ServerResource serverResource = appResource.getServerResource();
                    String hostName = serverResource.getHostName();
                    String serverResourceName = serverResource.getName();
                    Integer serverResourceId = serverResource.getId();
                    String serverResourceUuid = serverResource.getUuid();
                    contentBuffer.append("\n@" + serverResourceName + "(" + hostName + ")\n");

                    Map<String, Object> referenceValueMap = monServerDao.selectReferenceValue(measurementName, hostName, cvMapDto);
                    if (referenceValueMap != null) {
                        logger.debug("referenceValueMap : {}", referenceValueMap);
                        List<String> metricNames = cvMapDto.getMetricNames();
                        for (String metricName : metricNames) {

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
                    } else {
                        status = StatusEnum.Error;
                        contentBuffer.append(MessageUtil.getMessage("failMetricInfo", measurementName));

                    }
                }
//                }else{
//
//                }

                MeasurementStatusDto mentStatusDto = new MeasurementStatusDto();
                mentStatusDto.setMeasurementId(measurementId);
                mentStatusDto.setContent(contentBuffer.toString());
                mentStatusDto.setStatus(status.getString());
                mentStatusDto.setMeasurementName(measurementName);

                appGroupStatusesResDto.addMeasurementStatus(mentStatusDto);
            }

            pageAppGroupStatusesResDto.addListItem(appGroupStatusesResDto);

        }

        return pageAppGroupStatusesResDto;
        //return null;
    }


    public PagingResVo<AppStatusesResDto> pageAppGroupAppStatuses(Integer monitoringGroupId, Integer appGroupId, PagingReqVo paging) {
        String key = null;
        String keyword = null;
        Map<String, Map<String, String>> influxQueryFilters = applicationProperties.getInfluxQueryFilters();


        Page<MgAppGroupApp> pageMgAppGroupApps = mgAppGroupAppRepository.findAllByMonGroupIdAndAppGroupId(paging.toPagingRequest(), monitoringGroupId, appGroupId);


        PagingResVo<AppStatusesResDto> pageAppStatusesResDtoPagingResVo = new PagingResVo<AppStatusesResDto>(pageMgAppGroupApps, false);

        List<MgAppGroupApp> appGroupApps = pageMgAppGroupApps.getContent();



        List<AppResource> appResources = new ArrayList<AppResource>();

        for(MgAppGroupApp mgAppGroupApp : appGroupApps){
            appResources.add(mgAppGroupApp.getAppResource());
        }

        List<AppStatusesResDto> appStatusesResDtos = monCommonService.pageAppStatuses(monitoringGroupId, appResources);
        pageAppStatusesResDtoPagingResVo.setList(appStatusesResDtos);

        return pageAppStatusesResDtoPagingResVo;
    }


    public PagingResVo<EventResDto> pageEvent(Integer monitoringGroupId, Integer appGroupId, PagingReqVo paging, SearchReqVo search){

        List<MgAppGroupApp> mgAppGroupApps = mgAppGroupAppRepository.findAllByMonGroupIdAndAppGroupId(monitoringGroupId, appGroupId);

        List<Integer> resourceIds = new ArrayList<Integer>();

        for(MgAppGroupApp mgAppGroupApp : mgAppGroupApps){
            resourceIds.add(mgAppGroupApp.getAppResourceId());
        }

        PagingResVo<EventResDto> pagingResVo = monCommonService.pageEvent(monitoringGroupId, ResourceType.APPLICATION_GROUP, resourceIds, paging, search);

        return pagingResVo;
    }


    public PagingResVo<LogResDto> pageLog(Integer monitoringGroupId, Integer appGroupId, PagingReqVo paging, SearchReqVo search){
        List<MgAppGroupApp> mgAppGroupApps = mgAppGroupAppRepository.findAllByMonGroupIdAndAppGroupId(monitoringGroupId, appGroupId);

        List<Integer> resourceIds = new ArrayList<Integer>();

        for(MgAppGroupApp mgAppGroupApp : mgAppGroupApps){
            resourceIds.add(mgAppGroupApp.getAppResourceId());
        }

        PagingResVo<LogResDto> pagingResVo = monCommonService.pageServerLog(resourceIds, paging, search);

        return pagingResVo;
    }






}
