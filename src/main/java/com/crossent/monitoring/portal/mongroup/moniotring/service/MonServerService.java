package com.crossent.monitoring.portal.mongroup.moniotring.service;

import com.crossent.monitoring.portal.common.constants.Constants;
import com.crossent.monitoring.portal.common.constants.StatusEnum;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.CriticalValueInterface;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.MgServerTitleMap;
import com.crossent.monitoring.portal.jpa.repository.MeasurementRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerCriticalValueRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerTitleMapRepository;
import com.crossent.monitoring.portal.mongroup.moniotring.dao.MonServerDao;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.CriticalValueMapDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.MeasurementStatusDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ProcessStatusDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerStatusesResDto;
import com.crossent.monitoring.portal.mongroup.moniotring.util.MonitoringUtil;
import jdk.net.SocketFlow;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MonServerDao monServerDao;

    public PagingResVo<ServerStatusesResDto> getServerStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search) {

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

        //모니터링 대상서버 목록
        for (MgServer mgServer : mgServers) {
            String hostName = mgServer.getServerResource().getHostName();
            ServerStatusesResDto serverStatusesResDto = new ServerStatusesResDto();
            serverStatusesResDto.setServerResourceId(mgServer.getServerResourceId());
            serverStatusesResDto.setServerResourceName(mgServer.getServerResource().getName());
            serverStatusesResDto.setHostName(hostName);



            logger.debug("mgServer {} ::", mgServers);
            Collection<MgServerTitleMap> mgServerTitleMaps = mgServerTitleMapRepository.findAllByMonGroupId(monitoringGroupId);


            for (MgServerTitleMap map : mgServerTitleMaps) {

                StringBuilder contentBuffer = new StringBuilder();
                Integer mId = map.getMeasurementId();
                String mName = map.getMeasurements().getName();

                //title입력
                serverStatusesResDto.addTile(mName);

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

                processStatusDto.setStandardCnt(0L);
                processStatusDto.setNomalCnt(0L);
                processStatusDto.setAbnomalCnt(0L);

                procContent.append(MessageUtil.getMessage("failProcessInfo", hostName));

            }else{
                Long normalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_NORMAL)));
                Long abnormalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_ABNORMAL)));
                Long totalCnt = Math.round(MonitoringUtil.toDouble(processInfoForServer.get(Constants.PROC_STAT_KEY_TOTAL)));

                if(abnormalCnt > 0){
                    procStatus = StatusEnum.Error;
                }
                processStatusDto.setStandardCnt(totalCnt);
                processStatusDto.setNomalCnt(normalCnt);
                processStatusDto.setAbnomalCnt(abnormalCnt);

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

    /*public Collection<Measurement> getServerStatuses(Integer monitoringGroupId) {

        MgServerTitleMap byMonGroupId = mgServerTitleMapRepository.findByMonGroupId(monitoringGroupId);

        Collection<Measurement> byId = measurementRepository.findById(byMonGroupId.getMeasurementId());
        logger.debug("byId : : {}", byId);
        return byId;
    }*/

}
