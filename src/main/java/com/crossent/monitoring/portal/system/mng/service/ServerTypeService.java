package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.system.mng.dto.AppInfoDto;
import com.crossent.monitoring.portal.system.mng.dto.MeasurementDto;
import com.crossent.monitoring.portal.system.mng.dto.MetricDto;
import com.crossent.monitoring.portal.system.mng.dto.ServerTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServerTypeService  {
    private static Logger logger = LoggerFactory.getLogger(ServerTypeService.class);

    @Autowired
    ServerTypeRepository serverTypeRepository;

    @Autowired
    ServerTypeMeasurementMapRepository serverTypeMeasurementMapRepository;

    @Autowired
    ServerResourceRepository serverResourceRepository;

    @Autowired
    MgServerGroupRepository mgServerGroupRepository;

    @Autowired
    ServerTypeCriticalValueRepository serverTypeCriticalValueRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public PagingResVo<ServerTypeDto> pagingServerType(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        PagingResVo<ServerTypeDto> resPage = null;
        Map<String, String> keywords = searchReqVo.getKeywords();

        String key = null;
        String keyword = null;
        if(keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<ServerType> serverTypes = null;
        if(key == null){
            //TODO 전체조회
            serverTypes = serverTypeRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "name":
                {
                    serverTypes = serverTypeRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description":
                {
                    serverTypes = serverTypeRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        if(serverTypes != null){
            resPage = new PagingResVo<ServerTypeDto>(serverTypes, false);
            List<ServerType> content = serverTypes.getContent();

            List<ServerTypeDto> serverTypeDtos = new ArrayList<ServerTypeDto>();
            for(ServerType serverType : content){
                ServerTypeDto serverTypeDto = new ServerTypeDto();
                serverTypeDto.setId(serverType.getId());
                serverTypeDto.setName(serverType.getName());
                serverTypeDto.setDescription(serverType.getDescription());

                List<MeasurementDto> measurementDtos = new ArrayList<MeasurementDto>();
                Collection<Measurement> measurements = serverType.getMeasurements();
                for(Measurement measurement : measurements){
                    MeasurementDto measurementDto = new MeasurementDto();
                    measurementDto.setId(measurement.getId());
                    measurementDto.setName(measurement.getName());
                    measurementDto.setDescription(measurement.getDescription());

                    measurementDtos.add(measurementDto);
                }

                serverTypeDto.setMeasurements(measurementDtos);
                serverTypeDtos.add(serverTypeDto);
            }
            resPage.setList(serverTypeDtos);
        }
        //PagingResVo<ServerTypeDto> resPage = new PagingResVo<ServerTypeDto>(serverTypes, true);
        return resPage;
    }

    public void insertServerType(ServerType serverType) {

        ServerType inServerType = new ServerType();
        inServerType.setName(serverType.getName());
        inServerType.setDescription(serverType.getDescription());
        //inServerType.setMeasurements(serverType.getMeasurements());

        ServerType result = serverTypeRepository.save(inServerType);

        /*List<MeasurementDto> measurements = serverType.getMeasurements();
        for(MeasurementDto measurementDto : measurements){

            ServerTypeMeasurementMap serverTypeMeasurementMap = new ServerTypeMeasurementMap();
            serverTypeMeasurementMap.setServerTypeId(result.getId());
            serverTypeMeasurementMap.setMeasurementId(measurementDto.getId());
            serverTypeMeasurementMapRepository.save(serverTypeMeasurementMap);
        }*/

    }

    public void deleteServerTypes(Integer[] serverTypeIds) {

        // 서버 리소스, map, mg_server_group 삭제
        /*serverResourceRepository.deleteByServerTypeIdIn(serverTypeIds);
        mgServerGroupRepository.deleteByServerTypeIdIn(serverTypeIds);
        serverTypeMeasurementMapRepository.deleteByServerTypeIdIn(serverTypeIds);*/

        serverTypeRepository.deleteByIdIn(serverTypeIds);
    }

    public ServerType getServerType(Integer serverTypeId) {

        ServerType serverType = serverTypeRepository.findOne(serverTypeId);

        ServerType out = new ServerType();
        out.setId(serverType.getId());
        out.setName(serverType.getName());
        out.setDescription(serverType.getDescription());

        return out;
    }

    public ServerType updateServerType(Integer serverTypeId, ServerType serverType) {

        ServerType getData = serverTypeRepository.findOne(serverTypeId);

        if(getData == null) {
            return null;
        }
        getData.setName(serverType.getName());
        getData.setDescription(serverType.getDescription());

        ServerType updateData = serverTypeRepository.save(getData);

        return updateData;
    }

    public void deleteServerType(Integer serverTypeId) {

        serverTypeRepository.delete(serverTypeId);
    }

    public Collection<Measurement> getServerTypeMeasurement(Integer serverTypeId) {

        ServerType serverType = serverTypeRepository.findById(serverTypeId);
        Collection<Measurement> measurements = serverType.getMeasurements();

        return measurements;
    }

    public void insertServerTypeMeasurement(Integer serverTypeId, Integer[] measurementIds){

        for(Integer measurementId : measurementIds) {
            ServerTypeMeasurementMap map = new ServerTypeMeasurementMap();
            map.setServerTypeId(serverTypeId);
            map.setMeasurementId(measurementId);

            ServerTypeMeasurementMap result = serverTypeMeasurementMapRepository.save(map);
        }
    }

    public void deleteServerTypeMeasurements(Integer serverTypeId, Integer[] delMeasurements) {

        serverTypeMeasurementMapRepository.deleteByServerTypeIdAndMeasurementIdIn(serverTypeId, delMeasurements);
    }

    public void deleteServerTypeMeasurement(Integer serverTypeId, Integer measurementId) {

        serverTypeMeasurementMapRepository.deleteByServerTypeIdAndMeasurementId(serverTypeId, measurementId);
    }

    public PagingResVo<MeasurementDto> pagingServerTypeCritical(Integer serverTypeId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Page<ServerType> serverTypes = serverTypeRepository.findAllById(pagingReqVo.toPagingRequest(), serverTypeId);

        PagingResVo<MeasurementDto> resPage = new PagingResVo<MeasurementDto>(serverTypes, false);

        ServerType serverType = serverTypeRepository.findById(serverTypeId);

        Collection<Measurement> measurements = serverType.getMeasurements();
        List<MeasurementDto> measurementDtos = new ArrayList<MeasurementDto>();
        for(Measurement measurement : measurements){
            MeasurementDto measurementDto = new MeasurementDto();
            measurementDto.setId(measurement.getId());
            measurementDto.setName(measurement.getName());
            measurementDto.setDescription(measurement.getDescription());

            Collection<Metric> metrics = measurement.getMetrics();
            List<MetricDto> metricDtos = new ArrayList<MetricDto>();
            for(Metric metric : metrics){
                MetricDto metricDto = new MetricDto();
                metricDto.setId(metric.getId());
                metricDto.setName(metric.getName());

                ServerTypeCriticalValue id = serverTypeCriticalValueRepository.findAllByServerTypeIdAndMeasurementIdAndMetricId(serverTypeId, measurement.getId(), metric.getId());
                metricDto.setWarning(id.getWarning());
                metricDto.setCritical(id.getCritical());

                metricDtos.add(metricDto);
            }
            measurementDto.setMetrics(metricDtos);
            measurementDtos.add(measurementDto);
        }
        resPage.setList(measurementDtos);

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
        Page<ServerType> type = null;
        if (key == null) {
            //TODO 전체조회
            type = serverTypeRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "measurementName": {
                    type = serverTypeRepository.findAllByIdAndMeasurements_NameLike(pagingReqVo.toPagingRequest(), serverTypeId, keyword);
                }
                break;
            }
        }

        return resPage;
    }

    public ServerTypeCriticalValue updateServerTypeCritical(Integer serverTypeId, Integer measurementId, Integer metricId, ServerTypeCriticalValue serverTypeCriticalValue) {

        ServerTypeCriticalValue update = serverTypeCriticalValueRepository.findAllByServerTypeIdAndMeasurementIdAndMetricId(serverTypeId, measurementId, metricId);

        update.setWarning(serverTypeCriticalValue.getWarning());
        update.setCritical(serverTypeCriticalValue.getCritical());

        ServerTypeCriticalValue updateData = serverTypeCriticalValueRepository.save(update);

        return updateData;
    }

    public Collection<Metric> getServerTypeMeasurementMetrics(Integer serverTypeId, Integer measurementId){

//        ServerTypeMeasurementMap serverType = serverTypeMeasurementMapRepository.findByServerTypeId(serverTypeId);
        Measurement measurement = measurementRepository.findById(measurementId);
        Collection<Metric> metrics = measurement.getMetrics();

        return metrics;
    }
}
