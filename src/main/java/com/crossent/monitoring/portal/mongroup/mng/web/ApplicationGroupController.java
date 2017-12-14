package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgAppGroupDto;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerGroupDto;
import com.crossent.monitoring.portal.mongroup.mng.service.ApplicationGroupService;
import com.crossent.monitoring.portal.mongroup.mng.service.ServerGroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class ApplicationGroupController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ApplicationGroupController.class);

    @Autowired
    ApplicationGroupService applicationGroupService;

    @ApiOperation(value = "관리 어플리케이션 그룹 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups", method = RequestMethod.GET)
    public PagingResVo pagingAppGroup(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }
        PagingResVo<MgAppGroupDto> resPage = applicationGroupService.pagingAppGroup(monitoringGroupId, paging, search);
        if(logger.isDebugEnabled()) {
            logger.debug("resPage : {}", resPage);
        }
        return resPage;
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgAppGroup", value = "모니터링 어플리케이션그룹", required = true, dataType = "string", paramType = "body"),
    })
   @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups", method = RequestMethod.POST)
    public void createAppGroup(@PathVariable Integer monitoringGroupId, @RequestBody MgAppGroup mgAppGroup) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("mgAppGroup : {}", mgAppGroup);
        }
        applicationGroupService.createAppGroup(monitoringGroupId, mgAppGroup);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}", method = RequestMethod.GET)
    public MgAppGroupDto getAppGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
        }
        MgAppGroupDto mgAppGroupDto = applicationGroupService.getAppGroup(monitoringGroupId, appGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("mgAppGroupDto : {}", mgAppGroupDto);
        }
        return mgAppGroupDto;
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgAppGroup", value = "모니터링 어플리케이션그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}", method = RequestMethod.PUT)
    public void updateAppGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestBody MgAppGroup mgAppGroup) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("mgAppGroup : {}", mgAppGroup);
        }
        applicationGroupService.updateAppGroup(monitoringGroupId, appGroupId, mgAppGroup);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupIds", value = "어플리케이션그룹 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups", method = RequestMethod.DELETE)
    public void deleteAppGroups(@PathVariable Integer monitoringGroupId, @RequestParam Integer[] appGroupIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupIds : {}", Arrays.toString(appGroupIds));
        }
        applicationGroupService.deleteAppGroups(monitoringGroupId, appGroupIds);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 메저먼트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/measurements", method = RequestMethod.GET)
    public Collection<Measurement> getAppGroupMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
        }
        Collection<Measurement> measurements = applicationGroupService.getAppGroupMeasurements(monitoringGroupId, appGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("measurements : {}", measurements);
        }
        return measurements;
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 메저먼트 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/measurements" , method = RequestMethod.POST)
    public void insertAppGroupMeasurement(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestBody Integer[] measurementIds){

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("measurementIds : {}", Arrays.toString(measurementIds));
        }
        applicationGroupService.insertAppGroupMeasurement(monitoringGroupId, appGroupId, measurementIds);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 메저먼트 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/measurements" , method = RequestMethod.DELETE)
    public void deleteAppGroupMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestParam Integer[] measurementIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("measurementIds : {}", Arrays.toString(measurementIds));
        }
        applicationGroupService.deleteAppGroupMeasurements(monitoringGroupId, appGroupId, measurementIds);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 메트릭 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/measurements/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<MgAppGroupCriticalValue> getServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @PathVariable Integer measurementId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("measurementId : {}", measurementId);
        }
        Collection<MgAppGroupCriticalValue> appGroupMetrics = applicationGroupService.getAppGroupMetrics(monitoringGroupId, appGroupId, measurementId);
        if(logger.isDebugEnabled()) {
            logger.debug("appGroupMetrics : {}", appGroupMetrics);
        }
        return appGroupMetrics;
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 메트릭 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/metrics" , method = RequestMethod.POST)
    public void insertAppGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestBody Integer[] metricIds){

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("metricIds : {}", Arrays.toString(metricIds));
        }
        applicationGroupService.insertAppGroupMetrics(monitoringGroupId, appGroupId, metricIds);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 메트릭 임계치 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgAppGroupCriticalValue", value = "모니터링 어플리케이션그룹 임계치", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateAppGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @PathVariable Integer metricId, @RequestBody MgAppGroupCriticalValue mgAppGroupCriticalValue) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("metricId : {}", metricId);
            logger.debug("mgAppGroupCriticalValue : {}", mgAppGroupCriticalValue);
        }
        applicationGroupService.updateAppGroupMetrics(monitoringGroupId, appGroupId, metricId, mgAppGroupCriticalValue);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 메트릭 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/metrics" , method = RequestMethod.DELETE)
    public void deleteAppGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestParam Integer[] metricIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("metricIds : {}", Arrays.toString(metricIds));
        }
        applicationGroupService.deleteAppGroupMetrics(monitoringGroupId, appGroupId, metricIds);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 어플리케이션 리소스 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/apps" , method = RequestMethod.GET)
    public Collection<MgApp> getAppGroupAppResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
        }
        Collection<MgApp> mgApps = applicationGroupService.getAppGroupAppResource(monitoringGroupId, appGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("mgApps : {}", mgApps);
        }
        return mgApps;
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 어플리케이션 리소스 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceIds", value = "어플리케이션 리소스 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/apps" , method = RequestMethod.POST)
    public void insertAppGroupAppResources(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestBody Integer[] appResourceIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("appResourceIds : {}", Arrays.toString(appResourceIds));
        }
        applicationGroupService.insertAppGroupAppResources(monitoringGroupId, appGroupId, appResourceIds);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 어플리케이션 리소스 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceIds", value = "어플리케이션 리소스 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/apps" , method = RequestMethod.DELETE)
    public void deleteAppGroupAppResources(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestParam Integer[] appResourceIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("appResourceIds : {}", Arrays.toString(appResourceIds));
        }
        applicationGroupService.deleteAppGroupAppResources(monitoringGroupId, appGroupId, appResourceIds);
    }

    @ApiOperation(value = "관리 어플리케이션 그룹 어플리케이션 리소스 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appGroupId", value = "어플리케이션그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/apps/{appResourceId}" , method = RequestMethod.DELETE)
    public void deleteAppGroupAppResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @PathVariable Integer appResourceId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("appResourceId : {}", appResourceId);
        }
        applicationGroupService.deleteAppGroupAppResource(monitoringGroupId, appGroupId, appResourceId);
    }
}
