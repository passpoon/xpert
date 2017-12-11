package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MgApp;
import com.crossent.monitoring.portal.jpa.domain.MgAppCriticalValue;
import com.crossent.monitoring.portal.jpa.repository.MgAppCriticalValueRepository;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgAppDto;
import com.crossent.monitoring.portal.mongroup.mng.service.ApplicationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ApplicationController extends BaseController {

    @Autowired
    ApplicationService applicationService;


    @ApiOperation(value = "관리 어플리케이션 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps", method = RequestMethod.GET)
    public PagingResVo pagingMonApp(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgAppDto> resPage = applicationService.pagingMonApp(monitoringGroupId, paging, search);

        return resPage;
    }

    @ApiOperation(value = "관리 어플리케이션 정보 수정(대시보드, 모니터링 여부)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgApp", value = "모니터링 어플리케이션", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}", method = RequestMethod.PUT)
    public void updateMonApp(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @RequestBody MgApp mgApp) {

        applicationService.updateMonApp(monitoringGroupId, appResourceId, mgApp);
    }

    @ApiOperation(value = "관리 어플리케이션 메저먼트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/measurements", method = RequestMethod.GET)
    public Collection<Measurement> getMonAppMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId) {

        Collection<Measurement> measurements = applicationService.getMonAppMeasurements(monitoringGroupId, appResourceId);

        return measurements;
    }

    @ApiOperation(value = "관리 서버 메저먼트 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/measurements" , method = RequestMethod.POST)
    public void insertMonAppMeasurement(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @RequestBody Integer[] measurementIds){

        applicationService.insertMonAppMeasurement(monitoringGroupId, appResourceId, measurementIds);
    }

    @ApiOperation(value = "관리 어플리케이션 메저먼트 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/measurements" , method = RequestMethod.DELETE)
    public void deleteMonAppMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @RequestParam Integer[] measurementIds) {

        applicationService.deleteMonAppMeasurements(monitoringGroupId, appResourceId, measurementIds);
    }

    @ApiOperation(value = "관리 어플리케이션 임계치 설정을 위한 메트릭 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/metrics", method = RequestMethod.GET)
    public Collection<MgAppCriticalValue> getMonAppMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId) {

        Collection<MgAppCriticalValue> appMetrics = applicationService.getMonAppMetrics(monitoringGroupId, appResourceId);

        return appMetrics;
    }

    @ApiOperation(value = "관리 어플리케이션 메트릭 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/metrics" , method = RequestMethod.POST)
    public void insertMonAppMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @RequestBody Integer[] metricIds){

        applicationService.insertMonAppMetrics(monitoringGroupId, appResourceId, metricIds);
    }


    @ApiOperation(value = "관리 어플리케이션 메트릭별 임계치 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgAppCriticalValue", value = "모니터링 어플리케이션 메트릭 임계치", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateMonAppMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @PathVariable Integer metricId, @RequestBody MgAppCriticalValue mgAppCriticalValue) {

        applicationService.updateMonAppMetrics(monitoringGroupId, appResourceId, metricId, mgAppCriticalValue);
    }

    @ApiOperation(value = "관리 어플리케이션 메트릭 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/metrics" , method = RequestMethod.DELETE)
    public void deleteAppMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @RequestParam Integer[] metricIds) {

        applicationService.deleteMonAppMetrics(monitoringGroupId, appResourceId, metricIds);
    }
}
