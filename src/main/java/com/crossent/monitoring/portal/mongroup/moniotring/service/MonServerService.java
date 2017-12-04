package com.crossent.monitoring.portal.mongroup.moniotring.service;

import com.crossent.monitoring.portal.common.constants.*;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.util.DateUtil;
import com.crossent.monitoring.portal.common.lib.util.MapUtil;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.lib.util.StringUtil;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.mongroup.moniotring.dao.MonServerDao;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.*;
import com.crossent.monitoring.portal.mongroup.moniotring.util.MonitoringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonServerService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MonServerService.class);

    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgServerTitleMapRepository mgServerTitleMapRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MgServerCriticalValueRepository mgServerCriticalValueRepository;



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

    public PagingResVo<ServerStatusesResDto> pageServerStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search) {

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
                keyword = "%" + keyword + "%";
            }
        }


        Page<MgServer> pageMgServer = null;
        if (key == null) {
            pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId, "Y");
        } else {

            switch (key) {
                case "hostName": {
                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndServerResource_HostNameLike(paging.toPagingRequest(), monitoringGroupId, "Y", keyword);
                }
                break;
                case "serverName": {
                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndServerResource_NameLike(paging.toPagingRequest(),  monitoringGroupId,"Y" , keyword);
                }
                default:
                    throw new BusinessException(MessageUtil.getMessage("unDefSearchKey", key));

            }
        }


        PagingResVo<ServerStatusesResDto> pageServerStatusesResDtoPagingResVo = new PagingResVo<ServerStatusesResDto>(pageMgServer, false);


        List<MgServer> mgServers = pageMgServer.getContent();
        if (logger.isDebugEnabled()) {
            logger.debug("mgServers : {}", mgServers);
        }

        logger.debug("mgServer {} ::", mgServers);
        Collection<MgServerTitleMap> mgServerTitleMaps = mgServerTitleMapRepository.findAllByMonGroupId(monitoringGroupId);

        //title셋팅
        for(MgServerTitleMap titleMap : mgServerTitleMaps){

            pageServerStatusesResDtoPagingResVo.addTile(titleMap.getMeasurements().getName());

        }

        //모니터링 대상서버 목록
        for (MgServer mgServer : mgServers) {
            String hostName = mgServer.getServerResource().getHostName();
            ServerStatusesResDto serverStatusesResDto = new ServerStatusesResDto();
            serverStatusesResDto.setServerResourceId(mgServer.getServerResourceId());
            serverStatusesResDto.setServerResourceName(mgServer.getServerResource().getName());
            serverStatusesResDto.setHostName(hostName);



            logger.debug("mgServer {} ::", mgServers);



            for (MgServerTitleMap map : mgServerTitleMaps) {

                StringBuilder contentBuffer = new StringBuilder();
                Integer mId = map.getMeasurementId();
                String mName = map.getMeasurements().getName();

                //title입력
                //serverStatusesResDto.addTile(mName);

                Collection<MgServerCriticalValue> criticalValues = mgServerCriticalValueRepository
                                                                           .findAllByMonGroupIdAndServerResourceIdAndMetric_MeasurementId(monitoringGroupId,
                                                                                   mgServer.getServerResourceId(), mId);


                if(logger.isDebugEnabled()){
                    logger.debug("criticalValues size : {}", criticalValues.size());
                    logger.debug("criticalValues  : {}", criticalValues);
                }


                StatusEnum status = StatusEnum.NA;

                if(criticalValues != null && criticalValues.size() != 0){
                    List<CriticalValueInterface> metriIfs = MonitoringUtil.criticalCollectionToInterface(criticalValues);

                    CriticalValueMapDto cvMapDto = new CriticalValueMapDto(metriIfs);

                    logger.debug("cvMapDto : {}", cvMapDto);


                    Map<String, Object> referenceValueMap = monServerDao.selectReferenceValue(mName, hostName, cvMapDto);

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
                        contentBuffer.append(MessageUtil.getMessage("failMetricInfo", mName));

                    }
                }else{
                    //status = StatusEnum.NA;
                    contentBuffer.append(MessageUtil.getMessage("unDefCriticalVal")+"\n"); //정의된 임계치가 없습니다.
                }

                MeasurementStatusDto mentStatusDto = new MeasurementStatusDto();
                mentStatusDto.setMeasurementId(mId);
                mentStatusDto.setContent(contentBuffer.toString());
                mentStatusDto.setStatus(status.getString());
                mentStatusDto.setMeasurementName(mName);

                serverStatusesResDto.addMeasurementStatus(mentStatusDto);



            }




            //process정보 조회
            StatusEnum procStatus = StatusEnum.Nomal;
            StringBuilder procContent = new StringBuilder();
            ProcessStatusDto processStatusDto  = new ProcessStatusDto();

            Map<String, Object> processInfoForServer = monServerDao.selectProcessInfoForServer(hostName);

            if(processInfoForServer == null){
                procStatus = StatusEnum.Error;

                processStatusDto.setTotalCnt(0L);
                processStatusDto.setNormalCnt(0L);
                processStatusDto.setAbnormalCnt(0L);

                procContent.append(MessageUtil.getMessage("failProcessInfo", hostName));

            }else{
                Long normalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_NORMAL)));
                Long abnormalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_ABNORMAL)));
                Long totalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_TOTAL)));

                if(abnormalCnt > 0){
                    procStatus = StatusEnum.Error;
                }
                processStatusDto.setTotalCnt(totalCnt);
                processStatusDto.setNormalCnt(normalCnt);
                processStatusDto.setAbnormalCnt(abnormalCnt);

                for(String keys : Constants.PROC_STAT_KEYS){
                    procContent.append(keys + " : " + processInfoForServer.get(keys)+"\n");
                }


            }

            processStatusDto.setStatus(procStatus.getString());
            processStatusDto.setContent(procContent.toString());

            serverStatusesResDto.setProcessStatus(processStatusDto);

            pageServerStatusesResDtoPagingResVo.addListItem(serverStatusesResDto);




        }


        return pageServerStatusesResDtoPagingResVo;
    }

    public ServerDetailStatusDto getServerDetailStatus(Integer monitoringGroupId, Integer serverResourceId, SearchReqVo search) {

        ServerResource serverResource = serverResourceRepository.findById(serverResourceId);

        if(serverResource == null){

            throw new BusinessException(MessageUtil.getMessage("noSearchServer", serverResourceId+""));
        }

        String rangeType = search.getRangeType();

        if(StringUtil.isEmpty(rangeType)){
            rangeType = "5m";
        }
        ServerType serverType = serverResource.getServerType();

        Collection<Measurement> measurements = serverType.getMeasurements();

        ServerDetailStatusDto serverDetailStatusDto = new ServerDetailStatusDto();

        serverDetailStatusDto.setServerResourceId(serverResourceId);
        serverDetailStatusDto.setServerName(serverResource.getName());
        serverDetailStatusDto.setHostName(serverResource.getHostName());
        serverDetailStatusDto.setIp(serverResource.getIp());

        for(Measurement measurement : measurements){
            MeasurementDetail measurementDetail = new MeasurementDetail();

            Integer measurementId = measurement.getId();
            String measurementName = measurement.getName();
            List<String> typeCodes = new ArrayList<String>();
            typeCodes.add(MetricType.INT.getCode());
            typeCodes.add(MetricType.DOUBLE.getCode());
            Collection<Metric> metrics = metricRepository.findAllByMeasurementIdAndMetricTypeCodeIn(measurementId, typeCodes);



            measurementDetail.setMeasurementId(measurementId);
            measurementDetail.setMeasurementName(measurementName);

            for(Metric metric : metrics) {
                measurementDetail.addTitle(metric.getName());
            }


            List<Map<String, String>> listMetrics = monServerDao.listMetrics(serverResource.getHostName(), measurementName, rangeType, metrics);

            logger.debug("listMetrics : {}", listMetrics);
            measurementDetail.setRows(listMetrics);
            serverDetailStatusDto.addMeasurement(measurementDetail);

        }

        return serverDetailStatusDto;
    }


    public PagingResVo<EventResDto> pageEvent(Integer monGroupId, Integer serverResourceId, PagingReqVo pagingReqVo, SearchReqVo search){
//        String key = null;
//        String keyword = null;
        PagingResVo<EventResDto> eventResPage = null;

        Map<String, String> keywords = search.getKeywords();


        List<String> serverResourceTypes = new ArrayList<>();
        List<String> stateCodes = new ArrayList<>();
        Page<EventHistory> eventHistoryPage = null;


        if (keywords != null) {
            List<String> listKey = MapUtil.listKeys(keywords);
            for (String key: listKey) {
                key = key.toUpperCase();
                String keyword = keywords.get(key);
                    switch (key) {
                        case "RESOURCE-TYPE":
                            switch(keyword){
                                case "SERVER":
                                    serverResourceTypes.add(ResourceType.SERVER.getCode());
                                case "LOG":
                                    serverResourceTypes.add(ResourceType.LOG.getCode());
                            }

                        case "STATE":
                            switch(keyword){
                                case "NORMAL":
                                    stateCodes.add(ServerState.NORMAL.getCode());
                                case "WARNING":
                                    stateCodes.add(ServerState.WARNING.getCode());
                                case "CRITICAL":
                                    stateCodes.add(ServerState.CRITICAL.getCode());
                                    break;
                                case "START":
                                    stateCodes.add(LogState.START.getCode());
                                    break;
                                case "STOP":
                                    stateCodes.add(LogState.STOP.getCode());
                                    break;
                                case "DEBUG":
                                    stateCodes.add(LogState.DEBUG.getCode());
                                case "INFO":
                                    stateCodes.add(LogState.INFO.getCode());
                                case "WARN":
                                    stateCodes.add(LogState.WARN.getCode());
                                case "ERROR":
                                    stateCodes.add(LogState.ERROR.getCode());
                                    break;
                            }
                    }

            }

        }

        if(serverResourceTypes.size() == 0){
            serverResourceTypes.add(ResourceType.SERVER.getCode());
            serverResourceTypes.add(ResourceType.LOG.getCode());
        }

        if(stateCodes.size() == 0){
            stateCodes.add(ServerState.NORMAL.getCode());
                stateCodes.add(ServerState.WARNING.getCode());
                stateCodes.add(ServerState.CRITICAL.getCode());
                stateCodes.add(LogState.START.getCode());
                stateCodes.add(LogState.STOP.getCode());
                stateCodes.add(LogState.DEBUG.getCode());
                stateCodes.add(LogState.INFO.getCode());
                stateCodes.add(LogState.WARN.getCode());
                stateCodes.add(LogState.ERROR.getCode());
        }

        eventHistoryPage = eventHistoryRepository.findAllByMonGroupIdAndResourceIdAndResourceTypeInAndStateCodeCodeInOrderByUpdateDttmDescIdDesc(pagingReqVo.toPagingRequest(), monGroupId, serverResourceId, serverResourceTypes, stateCodes);

        eventResPage = new PagingResVo<EventResDto>(eventHistoryPage, false);

        List<EventHistory> content = eventHistoryPage.getContent();

        for(EventHistory eventHistory : content){
            EventResDto eventResDto = new EventResDto();
            eventResDto.setHostName(eventHistory.getHostname());
            eventResDto.setContents(eventHistory.getContents());
            eventResDto.setId(eventHistory.getId());
            eventResDto.setIp(eventHistory.getIp());
            eventResDto.setMonGroupId(eventHistory.getMonGroupId());
            eventResDto.setProgram(eventHistory.getProgram());
            eventResDto.setRegiDttm(convertDateFormat(eventHistory.getRegistDttm()));
            eventResDto.setUpdateDttm(convertDateFormat(eventHistory.getUpdateDttm()));
            eventResDto.setResourceType(eventHistory.getResourceType());
            eventResDto.setResourceUuid(eventHistory.getResourceUuid());
            eventResDto.setState(eventHistory.getStateCode().getState());

            eventResPage.addListItem(eventResDto);
        }

        return eventResPage;
    }


    private String convertDateFormat(String date){
        return DateUtil.dateToString(DateUtil.stringToDate(date, DateUtil.DATE_HMS_PATTERN), DateUtil.DATE_TIME_PATTERN);

    }
}
