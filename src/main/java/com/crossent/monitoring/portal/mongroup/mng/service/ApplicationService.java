package com.crossent.monitoring.portal.mongroup.mng.service;

import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgAppDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService {

    private static Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    MgAppRepository mgAppRepository;

    @Autowired
    MgAppCriticalValueRepository mgAppCriticalValueRepository;

    @Autowired
    AppResourceRepository appResourceRepository;

    @Autowired
    AppInfoRepository appInfoRepository;

    @Autowired
    AppInfoMeasurementMapRepository appInfoMeasurementMapRepository;

    public PagingResVo<MgAppDto> pagingMonApp(Integer monitoringGroupId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

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
        Page<MgApp> mgApps = null;
        logger.debug("key : {}", key);
        logger.debug("keyword : {}", keyword);
        if (key == null) {
            //TODO 전체조회
            mgApps = mgAppRepository.findAllByMonGroupId(pagingReqVo.toPagingRequest(), monitoringGroupId);
        } else {
            switch (key) {
                case "applicationName": {
                    mgApps = mgAppRepository.findAllByMonGroupIdAndAppResource_NameLike(monitoringGroupId, pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                default:
                    throw new BusinessException("unDefSearchKey", key);
            }
        }

        PagingResVo<MgAppDto> resPage = new PagingResVo<MgAppDto>(mgApps, false);

        List<MgApp> content = mgApps.getContent();
        if(logger.isDebugEnabled()){
            logger.debug("content : {}", content);
        }

        for(MgApp mgApp : content){
            MgAppDto mgAppDto = new MgAppDto();
            mgAppDto.setMonGroupId(mgApp.getMonGroupId());
            mgAppDto.setAppResourceId(mgApp.getAppResourceId());
            mgAppDto.setApplicationName(mgApp.getAppResource().getName());
            mgAppDto.setHostName(mgApp.getAppResource().getServerResource().getHostName());
            mgAppDto.setMonitoringYn(mgApp.getMonitoringYn());
            mgAppDto.setDashboardYn(mgApp.getDashboardYn());

            resPage.addListItem(mgAppDto);
        }

        return resPage;
    }

    public MgApp updateMonApp(Integer monitoringGroupId, Integer appResourceId, MgApp mgApp) {

        MgApp updateApp = mgAppRepository.findByMonGroupIdAndAppResourceId(monitoringGroupId, appResourceId);
        if(logger.isDebugEnabled()){
            logger.debug("updateApp : {}", updateApp);
        }

        updateApp.setMonitoringYn(mgApp.getMonitoringYn());
        updateApp.setDashboardYn(mgApp.getDashboardYn());

        MgApp updateData = mgAppRepository.save(updateApp);

        return updateData;
    }

    // 메저먼트 조회
    public Collection<Measurement> getMonAppMeasurements(Integer monitoringGroupId, Integer appResourceId) {

        AppResource appResource = appResourceRepository.findById(appResourceId);
        if(logger.isDebugEnabled()){
            logger.debug("appResource : {}", appResource);
        }
        Integer appInfoId = appResource.getAppInfoId();

        AppInfo appInfo = appInfoRepository.findById(appInfoId);
        Collection<Measurement> measurements = appInfo.getMeasurements();

        return measurements;
    }

    // 메저먼트 추가
    public void insertMonAppMeasurement(Integer monitoringGroupId, Integer appResourceId, Integer[] measurementIds){

        AppResource appResource = appResourceRepository.findById(appResourceId);
        if(logger.isDebugEnabled()){
            logger.debug("appResource : {}", appResource);
        }
        Integer appInfoId = appResource.getAppInfoId();

        for(Integer measurementId : measurementIds) {
            AppInfoMeasurementMap map = new AppInfoMeasurementMap();
            map.setAppInfoId(appInfoId);
            map.setMeasurementId(measurementId);

            AppInfoMeasurementMap result = appInfoMeasurementMapRepository.save(map);
        }
    }

    // 메저먼트 삭제
    public void deleteMonAppMeasurements(Integer monitoringGroupId, Integer appResourceId, Integer[] measurementIds) {

        AppResource appResource = appResourceRepository.findById(appResourceId);
        if(logger.isDebugEnabled()){
            logger.debug("appResource : {}", appResource);
        }
        Integer appInfoId = appResource.getAppInfoId();

        appInfoMeasurementMapRepository.deleteByAppInfoIdAndMeasurementIdIn(appInfoId, measurementIds);
    }

    public Collection<MgAppCriticalValue> getMonAppMetrics(Integer monitoringGroupId, Integer appResourceId, Integer measurementId) {

        Collection<MgAppCriticalValue> mgAppCriticalValues = mgAppCriticalValueRepository.findAllByMonGroupIdAndAppResourceIdAndMetric_MeasurementId(monitoringGroupId, appResourceId, measurementId);
        if(logger.isDebugEnabled()){
            logger.debug("mgAppCriticalValues : {}", mgAppCriticalValues);
        }
        /*MgApp mgApp = mgAppRepository.findByMonGroupIdAndAppResourceId(monitoringGroupId, appResourceId);
        Collection<MgAppCriticalValue> mgAppCriticalValues = mgApp.getMgAppCriticalValues();*/
        return mgAppCriticalValues;
    }

    public void insertMonAppMetrics(Integer monitoringGroupId, Integer appResourceId, Integer[] metricIds){

        for(Integer metricId : metricIds) {
            MgAppCriticalValue map = new MgAppCriticalValue();
            map.setMonGroupId(monitoringGroupId);
            map.setAppResourceId(appResourceId);
            map.setMetricId(metricId);

            MgAppCriticalValue result = mgAppCriticalValueRepository.save(map);
        }
    }

    public MgAppCriticalValue updateMonAppMetrics(Integer monitoringGroupId, Integer appResourceId, Integer metricId, MgAppCriticalValue mgAppCriticalValue) {

        MgAppCriticalValue updateAppMetric = mgAppCriticalValueRepository.findByMonGroupIdAndAppResourceIdAndMetricId(monitoringGroupId, appResourceId, metricId);

        updateAppMetric.setWarning(mgAppCriticalValue.getWarning());
        updateAppMetric.setCritical(mgAppCriticalValue.getCritical());

        return updateAppMetric;
    }

    public void deleteMonAppMetrics(Integer monitoringGroupId, Integer appResourceId, Integer[] metricIds) {

        mgAppCriticalValueRepository.deleteByMonGroupIdAndAppResourceIdAndMetricIdIn(monitoringGroupId, appResourceId, metricIds);
    }
}
