package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
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
    public PagingResVo pagingApp(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgAppDto> resPage = applicationService.pagingApp(monitoringGroupId, paging, search);

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
    public void updateApp(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @RequestBody MgApp mgApp) {

        applicationService.updateApp(monitoringGroupId, appResourceId, mgApp);
    }

    @ApiOperation(value = "관리 어플리케이션 임계치 설정을 위한 메트릭 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/metrics", method = RequestMethod.GET)
    public Collection<MgAppCriticalValue> getAppMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId) {

        Collection<MgAppCriticalValue> appMetrics = applicationService.getAppMetrics(monitoringGroupId, appResourceId);

        return appMetrics;
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
    public void updateAppMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @PathVariable Integer metricId, @RequestBody MgAppCriticalValue mgAppCriticalValue) {

        applicationService.updateAppMetrics(monitoringGroupId, appResourceId, metricId, mgAppCriticalValue);
    }
}
