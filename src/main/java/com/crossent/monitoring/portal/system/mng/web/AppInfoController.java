package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.AppInfoCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.system.mng.dto.AppInfoDto;
import com.crossent.monitoring.portal.system.mng.dto.MeasurementDto;
import com.crossent.monitoring.portal.system.mng.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class AppInfoController extends BaseController {

    @Autowired
    AppInfoService appInfoService;

    @RequestMapping(value = "/system/management/app-infos", method = RequestMethod.GET)
    public PagingResVo pagingAppInfo(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<AppInfoDto> resPage = appInfoService.pagingAppInfo(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos", method = RequestMethod.POST)
    public void insertAppInfo(@RequestBody AppInfo appInfo){

        appInfoService.insertAppInfo(appInfo);
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos", method = RequestMethod.DELETE)
    public void deleteAppInfos(@RequestParam Integer[] appInfoIds){

        appInfoService.deleteAppInfos(appInfoIds);
    }

    @RequestMapping(value = "/system/management/app-infos/{appInfoId}", method = RequestMethod.GET)
    public AppInfo getAppInfo(@PathVariable Integer appInfoId){

        AppInfo getAppInfo =  appInfoService.getAppInfo(appInfoId);

        return getAppInfo;
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}", method = RequestMethod.PUT)
    public void updateAppInfo(@PathVariable Integer appInfoId, @RequestBody AppInfo appInfo){

        appInfoService.updateAppInfo(appInfoId, appInfo);
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}", method = RequestMethod.DELETE)
    public void deleteAppInfo(@PathVariable Integer appInfoId){

        appInfoService.deleteAppInfo(appInfoId);
    }

    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements" , method = RequestMethod.GET)
    public Collection<Measurement> getAppInfoMeasurements(@PathVariable Integer appInfoId){

        Collection<Measurement> measurement = appInfoService.getAppInfoMeasurements(appInfoId);
        return measurement;
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements" , method = RequestMethod.POST)
    public void insertAppInfoMeasurements(@PathVariable Integer appInfoId, @RequestBody Integer[] measurementIds){

        appInfoService.insertAppInfoMeasurements(appInfoId, measurementIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements" , method = RequestMethod.DELETE)
    public void deleteAppInfoMeasurements(@PathVariable Integer appInfoId, @RequestParam Integer[] measurementIds) {

        appInfoService.deleteAppInfoMeasurements(appInfoId, measurementIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}" , method = RequestMethod.DELETE)
    public void deleteAppInfoMeasurement(@PathVariable Integer appInfoId, @PathVariable Integer measurementId) {

        appInfoService.deleteAppInfoMeasurement(appInfoId, measurementId);
    }

    /*@RequestMapping(value = "/system/management/app-infos/{appInfoId}/critical", method = RequestMethod.GET)
    public PagingResVo pagingAppInfoCritical(@PathVariable Integer appInfoId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MeasurementDto> resPage = appInfoService.pagingAppInfoCritical(appInfoId, paging, search);

        return resPage;
    }*/

    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<AppInfoCriticalValue> getAppInfoMeasurementMetrics(@PathVariable Integer appInfoId, @PathVariable Integer measurementId) {

        Collection<AppInfoCriticalValue> appInfoCriticalValues = appInfoService.getAppInfoMeasurementMetrics(appInfoId, measurementId);

        return appInfoCriticalValues;
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateAppInfoCritical(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @PathVariable Integer metricId, @RequestBody AppInfoCriticalValue appInfoCriticalValue) {

        appInfoService.updateAppInfoCritical(appInfoId, measurementId, metricId, appInfoCriticalValue);
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}" , method = RequestMethod.POST)
    public void insertAppInfoMeasurementMetrics(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @RequestBody Integer[] metricIds){

        appInfoService.insertAppInfoMeasurementMetrics(appInfoId, measurementId, metricIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics" , method = RequestMethod.DELETE)
    public void deleteAppInfoMeasurementsMetrics(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @RequestParam Integer[] metricIds) {

        appInfoService.deleteAppInfoMeasurementsMetrics(appInfoId, measurementId, metricIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics/{metricId}" , method = RequestMethod.DELETE)
    public void deleteAppInfoMeasurementsMetric(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @PathVariable Integer metricId) {

        appInfoService.deleteAppInfoMeasurementsMetrics(appInfoId, measurementId, metricId);
    }




}
