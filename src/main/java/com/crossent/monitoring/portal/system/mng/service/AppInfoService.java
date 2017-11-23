package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.AppInfoCriticalValueRepository;
import com.crossent.monitoring.portal.jpa.repository.AppInfoMeasurementMapRepository;
import com.crossent.monitoring.portal.jpa.repository.AppInfoRepository;
import com.crossent.monitoring.portal.jpa.repository.MeasurementRepository;
import com.crossent.monitoring.portal.system.mng.dto.AppInfoDto;
import com.crossent.monitoring.portal.system.mng.dto.MeasurementDto;
import com.crossent.monitoring.portal.system.mng.dto.MetricDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppInfoService {

    @Autowired
    AppInfoRepository appInfoRepository;

    @Autowired
    AppInfoMeasurementMapRepository appInfoMeasurementMapRepository;

    @Autowired
    AppInfoCriticalValueRepository appInfoCriticalValueRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public PagingResVo<AppInfoDto> pagingAppInfo(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        PagingResVo<AppInfoDto> resPage = null;
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
        Page<AppInfo> appInfos = null;
        if(key == null){
            //TODO 전체조회
            appInfos = appInfoRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "name":
                {
                    appInfos = appInfoRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description":
                {
                    appInfos = appInfoRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        if(appInfos != null) {
            resPage = new PagingResVo<AppInfoDto>(appInfos, false);
            List<AppInfo> content = appInfos.getContent();

            List<AppInfoDto> appInfoDtos = new ArrayList<AppInfoDto>();
            for(AppInfo appInfo : content){
                AppInfoDto appInfoDto = new AppInfoDto();
                appInfoDto.setId(appInfo.getId());
                appInfoDto.setName(appInfo.getName());
                appInfoDto.setDescription(appInfo.getDescription());

                List<MeasurementDto> measurementDtos = new ArrayList<MeasurementDto>();
                Collection<Measurement> measurements = appInfo.getMeasurements();
                for(Measurement measurement : measurements){
                    MeasurementDto measurementDto = new MeasurementDto();
                    measurementDto.setId(measurement.getId());
                    measurementDto.setName(measurement.getName());
                    measurementDto.setDescription(measurement.getDescription());

                    measurementDtos.add(measurementDto);
                }

                appInfoDto.setMeasurements(measurementDtos);
                appInfoDtos.add(appInfoDto);
            }
            resPage.setList(appInfoDtos);
        }

        return resPage;
    }


    public void insertAppInfo(AppInfo appInfo) {

        AppInfo inAppInfo = new AppInfo();
        inAppInfo.setName(appInfo.getName());
        inAppInfo.setDescription(appInfo.getDescription());

        AppInfo result = appInfoRepository.save(inAppInfo);

        /*List<MeasurementDto> measurements = appInfo.getMeasurements();
        for(MeasurementDto measurementDto : measurements){

            AppInfoMeasurementMap appInfoMeasurementMap = new AppInfoMeasurementMap();
            appInfoMeasurementMap.setAppInfoId(result.getId());
            appInfoMeasurementMap.setMeasurementId(measurementDto.getId());

            appInfoMeasurementMapRepository.save(appInfoMeasurementMap);
        }*/

    }


    public void deleteAppInfos(Integer[] appInfoIds) {

        appInfoRepository.deleteByIdIn(appInfoIds);
    }

    public AppInfo getAppInfo(Integer appInfoId) {

        AppInfo appInfo = appInfoRepository.findOne(appInfoId);

        AppInfo out = new AppInfo();
        out.setId(appInfo.getId());
        out.setName(appInfo.getName());
        out.setDescription(appInfo.getDescription());

        return out;
    }

    public AppInfo updateAppInfo(Integer appInfoId, AppInfo appInfo){

        AppInfo getData = appInfoRepository.findOne(appInfoId);

        if(getData == null) {
            return null;
        }
        getData.setName(appInfo.getName());
        getData.setDescription(appInfo.getDescription());

        AppInfo updateData = appInfoRepository.save(getData);

        return updateData;
    }

    public void deleteAppInfo(Integer appInfoId) {

        appInfoRepository.delete(appInfoId);
    }

    public Collection<Measurement> getAppInfoMeasurements(Integer appInfoId){

        AppInfo appInfo = appInfoRepository.findById(appInfoId);
        Collection<Measurement> measurements = appInfo.getMeasurements();

        return measurements;
    }

    public void insertAppInfoMeasurements(Integer appInfoId, Integer[] measurementIds){

        for(Integer measurementId : measurementIds) {
            AppInfoMeasurementMap map = new AppInfoMeasurementMap();
            map.setAppInfoId(appInfoId);
            map.setMeasurementId(measurementId);

            AppInfoMeasurementMap result = appInfoMeasurementMapRepository.save(map);
        }
    }

    public void deleteAppInfoMeasurements(Integer appInfoId, Integer[] measurementIds) {

        appInfoMeasurementMapRepository.deleteByAppInfoIdAndMeasurementIdIn(appInfoId, measurementIds);
    }

    public void deleteAppInfoMeasurement(Integer appInfoId, Integer measurementId) {

        appInfoMeasurementMapRepository.deleteByAppInfoIdAndMeasurementId(appInfoId, measurementId);
    }

    /*public PagingResVo<MeasurementDto> pagingAppInfoCritical(Integer appInfoId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Page<AppInfo> appInfos = appInfoRepository.findAllById(pagingReqVo.toPagingRequest(), appInfoId);

        PagingResVo<MeasurementDto> resPage = new PagingResVo<MeasurementDto>(appInfos, false);

        AppInfo appInfo = appInfoRepository.findById(appInfoId);

        Collection<Measurement> measurements = appInfo.getMeasurements();
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

                AppInfoCriticalValue id = appInfoCriticalValueRepository.findAllByAppInfoIdAndMeasurementIdAndMetricId(appInfoId, measurement.getId(), metric.getId());
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
        Page<AppInfo> infos = null;
        if (key == null) {
            //TODO 전체조회
            infos = appInfoRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "measurementName": {
                    infos = appInfoRepository.findAllByIdAndMeasurements_NameLike(pagingReqVo.toPagingRequest(), appInfoId, keyword);
                }
                break;
            }
        }

        return resPage;
    }*/

    public Collection<AppInfoCriticalValue> getAppInfoMeasurementMetrics(Integer appInfoId, Integer measurementId) {

        Collection<AppInfoCriticalValue> appInfoCriticalValues = appInfoCriticalValueRepository.findAllByAppInfoIdAndMeasurementId(appInfoId, measurementId);

        return  appInfoCriticalValues;
    }

    public AppInfoCriticalValue updateAppInfoCritical(Integer appInfoId, Integer measurementId, Integer metricId, AppInfoCriticalValue appInfoCriticalValue) {

        AppInfoCriticalValue update = appInfoCriticalValueRepository.findByAppInfoIdAndMeasurementIdAndMetricId(appInfoId, measurementId, metricId);

        update.setWarning(appInfoCriticalValue.getWarning());
        update.setCritical(appInfoCriticalValue.getCritical());

        AppInfoCriticalValue updateData = appInfoCriticalValueRepository.save(update);

        return updateData;
    }

    public void insertAppInfoMeasurementMetrics(Integer appInfoId, Integer measurementId, Integer[] metricIds){

        for(Integer metricId : metricIds) {
            AppInfoCriticalValue value = new AppInfoCriticalValue();
            value.setAppInfoId(appInfoId);
            value.setMeasurementId(measurementId);
            value.setMetricId(metricId);

            AppInfoCriticalValue result = appInfoCriticalValueRepository.save(value);
        }
    }

    public void deleteAppInfoMeasurementsMetrics(Integer appInfoId, Integer measurementId, Integer[] metricIds) {

        appInfoCriticalValueRepository.deleteByAppInfoIdAndMeasurementIdAndMetricIdIn(appInfoId, measurementId, metricIds);
    }

    public void deleteAppInfoMeasurementsMetrics(Integer appInfoId, Integer measurementId, Integer metricId) {

        appInfoCriticalValueRepository.deleteByAppInfoIdAndMeasurementIdAndMetricId(appInfoId, measurementId, metricId);
    }
}
