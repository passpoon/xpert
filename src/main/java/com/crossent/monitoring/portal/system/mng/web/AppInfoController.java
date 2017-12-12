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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class AppInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AppInfoController.class);

    @Autowired
    AppInfoService appInfoService;

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/app-infos", method = RequestMethod.GET)
    public PagingResVo pagingAppInfo(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<AppInfoDto> resPage = appInfoService.pagingAppInfo(paging, search);

        return resPage;
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfo", value = "어플리케이션 정보", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos", method = RequestMethod.POST)
    public void insertAppInfo(@RequestBody AppInfo appInfo){

        appInfoService.insertAppInfo(appInfo);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoIds", value = "어플리케이션 정보 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos", method = RequestMethod.DELETE)
    public void deleteAppInfos(@RequestParam Integer[] appInfoIds){

        appInfoService.deleteAppInfos(appInfoIds);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}", method = RequestMethod.GET)
    public AppInfo getAppInfo(@PathVariable Integer appInfoId){

        AppInfo getAppInfo =  appInfoService.getAppInfo(appInfoId);

        return getAppInfo;
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appInfo", value = "어플리케이션 정보", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}", method = RequestMethod.PUT)
    public void updateAppInfo(@PathVariable Integer appInfoId, @RequestBody AppInfo appInfo){

        appInfoService.updateAppInfo(appInfoId, appInfo);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}", method = RequestMethod.DELETE)
    public void deleteAppInfo(@PathVariable Integer appInfoId){

        appInfoService.deleteAppInfo(appInfoId);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements" , method = RequestMethod.GET)
    public Collection<Measurement> getAppInfoMeasurements(@PathVariable Integer appInfoId){

        Collection<Measurement> measurement = appInfoService.getAppInfoMeasurements(appInfoId);
        return measurement;
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 다중 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements" , method = RequestMethod.POST)
    public void insertAppInfoMeasurements(@PathVariable Integer appInfoId, @RequestBody Integer[] measurementIds){

        appInfoService.insertAppInfoMeasurements(appInfoId, measurementIds);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements" , method = RequestMethod.DELETE)
    public void deleteAppInfoMeasurements(@PathVariable Integer appInfoId, @RequestParam Integer[] measurementIds) {

        appInfoService.deleteAppInfoMeasurements(appInfoId, measurementIds);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
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

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 메트릭 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<AppInfoCriticalValue> getAppInfoMeasurementMetrics(@PathVariable Integer appInfoId, @PathVariable Integer measurementId) {

        Collection<AppInfoCriticalValue> appInfoCriticalValues = appInfoService.getAppInfoMeasurementMetrics(appInfoId, measurementId);

        return appInfoCriticalValues;
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 메트릭 임계치 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appInfoCriticalValue", value = "메트릭 임계치", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateAppInfoCritical(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @PathVariable Integer metricId, @RequestBody AppInfoCriticalValue appInfoCriticalValue) {

        appInfoService.updateAppInfoCritical(appInfoId, measurementId, metricId, appInfoCriticalValue);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 메트릭 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}" , method = RequestMethod.POST)
    public void insertAppInfoMeasurementMetrics(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @RequestBody Integer[] metricIds){

        appInfoService.insertAppInfoMeasurementMetrics(appInfoId, measurementId, metricIds);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 메트릭 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics" , method = RequestMethod.DELETE)
    public void deleteAppInfoMeasurementsMetrics(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @RequestParam Integer[] metricIds) {

        appInfoService.deleteAppInfoMeasurementsMetrics(appInfoId, measurementId, metricIds);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 정보 메저먼트 메트릭 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appInfoId", value = "어플리케이션 정보 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/app-infos/{appInfoId}/measurements/{measurementId}/metrics/{metricId}" , method = RequestMethod.DELETE)
    public void deleteAppInfoMeasurementsMetric(@PathVariable Integer appInfoId, @PathVariable Integer measurementId, @PathVariable Integer metricId) {

        appInfoService.deleteAppInfoMeasurementsMetrics(appInfoId, measurementId, metricId);
    }
}
