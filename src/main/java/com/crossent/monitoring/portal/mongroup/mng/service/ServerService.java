package com.crossent.monitoring.portal.mongroup.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServerService {
    private static Logger logger = LoggerFactory.getLogger(ServerService.class);

    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgServerCriticalValueRepository mgServerCriticalValueRepository;

    @Autowired
    ServerTypeRepository serverTypeRepository;

    @Autowired
    ServerResourceRepository serverResourceRepository;

    @Autowired
    ServerTypeMeasurementMapRepository serverTypeMeasurementMapRepository;

    @Autowired
    ServerTypeCriticalValueRepository serverTypeCriticalValueRepository;

    public PagingResVo<MgServerDto> pagingServer(Integer monitoringGroupId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if (keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<MgServer> mgServers = null;
        if (key == null) {
            //mgServers = mgServerRepository.findAll(pagingReqVo.toPagingRequest());
            mgServers = mgServerRepository.findAllByMonGroupId(pagingReqVo.toPagingRequest(), monitoringGroupId); // Dto 새로 생성, repository에 monGroupId로 조회
        } else {
            switch (key) {
                case "serverResourceName": {
                    mgServers = mgServerRepository.findAllByMonGroupIdAndServerResource_NameLike(pagingReqVo.toPagingRequest(), monitoringGroupId,  keyword);
                }
                break;
                case "hostName": {
                    mgServers = mgServerRepository.findAllByMonGroupIdAndServerResource_HostNameLike(pagingReqVo.toPagingRequest(), monitoringGroupId,  keyword);
                }
                break;
            }
        }

        PagingResVo<MgServerDto> resPage = new PagingResVo<MgServerDto>(mgServers, false);

        List<MgServer> content = mgServers.getContent();

        for(MgServer mgServer : content){
            MgServerDto mgServerDto = new MgServerDto();
            mgServerDto.setMonGroupId(mgServer.getMonGroupId());
            mgServerDto.setServerResourceId(mgServer.getServerResourceId());
            mgServerDto.setServerResourceName(mgServer.getServerResource().getName());
            mgServerDto.setHostName(mgServer.getServerResource().getHostName());
            mgServerDto.setMonitoringYn(mgServer.getMonitoringYn());
            mgServerDto.setDashboardYn(mgServer.getDashboardYn());

            resPage.addListItem(mgServerDto);
        }

        return resPage;
    }

    public MgServer updateServer(Integer monitoringGroupId, Integer serverResourceId, MgServer mgServer) {

        MgServer updateServer = mgServerRepository.findByMonGroupIdAndServerResourceId(monitoringGroupId, serverResourceId);



        updateServer.setMonitoringYn(mgServer.getMonitoringYn());
        updateServer.setDashboardYn(mgServer.getDashboardYn());

        MgServer updateData = mgServerRepository.save(updateServer);

        return updateData;
    }

    // 메저먼트 조회
    public Collection<Measurement> getMonServerMeasurements(Integer monitoringGroupId, Integer serverResourceId) {

        ServerResource serverResource = serverResourceRepository.findById(serverResourceId);
        Integer serverTypeId = serverResource.getServerTypeId();

        ServerType serverType = serverTypeRepository.findById(serverTypeId);
        Collection<Measurement> measurements = serverType.getMeasurements();

        return measurements;
    }

    // 메저먼트 추가
    public void insertMonServerMeasurement(Integer monitoringGroupId, Integer serverResourceId, Integer[] measurementIds){

        ServerResource serverResource = serverResourceRepository.findById(serverResourceId);
        Integer serverTypeId = serverResource.getServerTypeId();

        logger.debug("serverTypeId :", serverTypeId);
        for(Integer measurementId : measurementIds) {
            ServerTypeMeasurementMap map = new ServerTypeMeasurementMap();
            map.setServerTypeId(serverTypeId);
            map.setMeasurementId(measurementId);

            ServerTypeMeasurementMap result = serverTypeMeasurementMapRepository.save(map);
        }
    }

    // 메저먼트 삭제
    public void deleteMonServerMeasurements(Integer monitoringGroupId, Integer serverResourceId, Integer[] measurementIds) {

        ServerResource serverResource = serverResourceRepository.findById(serverResourceId);
        Integer serverTypeId = serverResource.getServerTypeId();

        serverTypeMeasurementMapRepository.deleteByServerTypeIdAndMeasurementIdIn(serverTypeId, measurementIds);
    }

    public Collection<MgServerCriticalValue> getMonServerMetrics(Integer monitoringGroupId, Integer serverResourceId) {

        MgServer mgServer = mgServerRepository.findByMonGroupIdAndServerResourceId(monitoringGroupId, serverResourceId);
        Collection<MgServerCriticalValue> mgServerCriticalValues = mgServer.getMgServerCriticalValues();

        return mgServerCriticalValues;
    }

    public void insertMonServerMetrics(Integer monitoringGroupId, Integer serverResourceId, Integer[] metricIds){

        for(Integer metricId : metricIds) {
            MgServerCriticalValue map = new MgServerCriticalValue();
            map.setMonGroupId(monitoringGroupId);
            map.setServerResourceId(serverResourceId);
            map.setMetricId(metricId);

            MgServerCriticalValue result = mgServerCriticalValueRepository.save(map);
        }
    }

    public MgServerCriticalValue updateMonServerMetrics(Integer monitoringGroupId, Integer serverResourceId, Integer metricId, MgServerCriticalValue mgServerCriticalValue) {

        MgServerCriticalValue updateServerMetric = mgServerCriticalValueRepository.findByMonGroupIdAndServerResourceIdAndMetricId(monitoringGroupId, serverResourceId, metricId);

        updateServerMetric.setWarning(mgServerCriticalValue.getWarning());
        updateServerMetric.setCritical(mgServerCriticalValue.getCritical());

        MgServerCriticalValue updateData = mgServerCriticalValueRepository.save(updateServerMetric);

        return updateData;
    }

    public void deleteMonServerMetrics(Integer monitoringGroupId, Integer serverResourceId, Integer[] metricIds) {

        mgServerCriticalValueRepository.deleteByMonGroupIdAndServerResourceIdAndMetricIdIn(monitoringGroupId, serverResourceId, metricIds);
    }
}
