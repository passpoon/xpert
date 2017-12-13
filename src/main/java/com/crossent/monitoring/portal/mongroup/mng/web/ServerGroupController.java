package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerGroupDto;
import com.crossent.monitoring.portal.mongroup.mng.service.ServerGroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class ServerGroupController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ServerGroupController.class);

    @Autowired
    ServerGroupService serverGroupService;

    @ApiOperation(value = "관리 서버 그룹 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.GET)
    public PagingResVo pagingServerGroup(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }
        PagingResVo<MgServerGroupDto> resPage = serverGroupService.pagingServerGroup(monitoringGroupId, paging, search);
        if(logger.isDebugEnabled()) {
            logger.debug("resPage : {}", resPage);
        }
        return resPage;
    }

    @ApiOperation(value = "관리 서버 그룹 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServerGroup", value = "모니터링 서버그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.POST)
    public void createServerGroup(@PathVariable Integer monitoringGroupId, @RequestBody MgServerGroup mgServerGroup) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("mgServerGroup : {}", mgServerGroup);
        }
        serverGroupService.createServerGroup(monitoringGroupId, mgServerGroup);
    }

    @ApiOperation(value = "관리 서버 그룹 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}", method = RequestMethod.GET)
    public MgServerGroupDto getServerGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
        }
        MgServerGroupDto mgServerGroupDto = serverGroupService.getServerGroup(monitoringGroupId, serverGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("mgServerGroupDto : {}", mgServerGroupDto);
        }
        return mgServerGroupDto;
    }

    @ApiOperation(value = "관리 서버 그룹 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServerGroup", value = "모니터링 서버그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}", method = RequestMethod.PUT)
    public void updateServerGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestBody MgServerGroup mgServerGroup) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("mgServerGroup : {}", mgServerGroup);
        }
        serverGroupService.updateServerGroup(monitoringGroupId, serverGroupId, mgServerGroup);
    }

    @ApiOperation(value = "관리 서버 그룹 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupIds", value = "서버 그룹 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.DELETE)
    public void deleteServerGroups(@PathVariable Integer monitoringGroupId, @RequestParam Integer[] serverGroupIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupIds : {}", Arrays.toString(serverGroupIds));
        }
        serverGroupService.deleteMgServerGroups(monitoringGroupId, serverGroupIds);
    }

    @ApiOperation(value = "관리 서버 그룹 메저먼트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/measurements", method = RequestMethod.GET)
    public Collection<Measurement> getServerGroupMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
        }
        Collection<Measurement> measurements = serverGroupService.getServerGroupMeasurements(monitoringGroupId, serverGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("measurements : {}", measurements);
        }
        return measurements;
    }

    @ApiOperation(value = "관리 서버 그룹 메저먼트 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/measurements" , method = RequestMethod.POST)
    public void insertServerGroupMeasurement(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestBody Integer[] measurementIds){

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("measurementIds : {}", Arrays.toString(measurementIds));
        }
        serverGroupService.insertServerGroupMeasurement(monitoringGroupId, serverGroupId, measurementIds);
    }

    @ApiOperation(value = "관리 서버 그룹 메저먼트 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/measurements" , method = RequestMethod.DELETE)
    public void deleteServerGroupMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestParam Integer[] measurementIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("measurementIds : {}", Arrays.toString(measurementIds));
        }
        serverGroupService.deleteServerGroupMeasurements(monitoringGroupId, serverGroupId, measurementIds);
    }

    @ApiOperation(value = "관리 서버 그룹 메트릭 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/metrics", method = RequestMethod.GET)
    public Collection<MgServerGroupCriticalValue> getServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
        }
        Collection<MgServerGroupCriticalValue> serverGroupMetrics = serverGroupService.getServerGroupMetrics(monitoringGroupId, serverGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("serverGroupMetrics : {}", serverGroupMetrics);
        }
        return serverGroupMetrics;
    }

    @ApiOperation(value = "관리 서버 그룹 메트릭 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/metrics" , method = RequestMethod.POST)
    public void insertServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestBody Integer[] metricIds){

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("metricIds : {}", Arrays.toString(metricIds));
        }
        serverGroupService.insertServerGroupMetrics(monitoringGroupId, serverGroupId, metricIds);
    }

    @ApiOperation(value = "관리 서버 그룹 메트릭 임계치 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServerGroupCriticalValue", value = "모니터링 서버 그룹 임계치", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @PathVariable Integer metricId, @RequestBody MgServerGroupCriticalValue mgServerGroupCriticalValue) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("metricId : {}", metricId);
        }
        serverGroupService.updateServerGroupMetrics(monitoringGroupId, serverGroupId, metricId, mgServerGroupCriticalValue);
    }

    @ApiOperation(value = "관리 서버 그룹 메트릭 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/metrics" , method = RequestMethod.DELETE)
    public void deleteServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestParam Integer[] metricIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("metricIds : {}", Arrays.toString(metricIds));
        }
        serverGroupService.deleteServerGroupMetrics(monitoringGroupId, serverGroupId, metricIds);
    }

    @ApiOperation(value = "관리 서버 그룹 서버 리소스 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers" , method = RequestMethod.GET)
    public Collection<MgServer> getServerGroupServerResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
        }
        Collection<MgServer> mgServers = serverGroupService.getServerGroupServerResource(monitoringGroupId, serverGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("mgServers : {}", mgServers);
        }
        return mgServers;
    }

    @ApiOperation(value = "관리 서버 그룹 서버 리소스 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceIds", value = "서버 리소스 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers" , method = RequestMethod.POST)
    public void insertServerGroupServerResources(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestBody Integer[] serverResourceIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("serverResourceIds : {}", Arrays.toString(serverResourceIds));
        }
        serverGroupService.insertServerGroupServerResources(monitoringGroupId, serverGroupId, serverResourceIds);
    }

    @ApiOperation(value = "관리 서버 그룹 서버 리소스 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceIds", value = "서버 리소스 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers" , method = RequestMethod.DELETE)
    public void deleteServerGroupServerResources(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestParam Integer[] serverResourceIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("serverResourceIds : {}", Arrays.toString(serverResourceIds));
        }
        serverGroupService.deleteServerGroupServerResources(monitoringGroupId, serverGroupId, serverResourceIds);
    }

    @ApiOperation(value = "관리 서버 그룹 서버 리소스 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers/{serverResourceId}" , method = RequestMethod.DELETE)
    public void deleteServerGroupServerResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @PathVariable Integer serverResourceId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverGroupId : {}", serverGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
        }
        serverGroupService.deleteServerGroupServerResource(monitoringGroupId, serverGroupId, serverResourceId);
    }
}
