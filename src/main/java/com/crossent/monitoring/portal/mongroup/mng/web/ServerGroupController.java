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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import java.util.Collection;

@RestController
public class ServerGroupController extends BaseController {

    @Autowired
    ServerGroupService serverGroupService;

    @ApiOperation(value = "관리 서버그룹 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.GET)
    public PagingResVo pagingServerGroup(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgServerGroupDto> resPage = serverGroupService.pagingServerGroup(monitoringGroupId, paging, search);

        return resPage;
    }

    @ApiOperation(value = "관리 서버그룹 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServerGroup", value = "모니터링 서버그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.POST)
    public void createServerGroup(@PathVariable Integer monitoringGroupId, @RequestBody MgServerGroup mgServerGroup) {

        serverGroupService.createServerGroup(monitoringGroupId, mgServerGroup);
    }

    @ApiOperation(value = "관리 서버그룹 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}", method = RequestMethod.GET)
    public MgServerGroupDto getServerGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        MgServerGroupDto mgServerGroupDto = serverGroupService.getServerGroup(monitoringGroupId, serverGroupId);

        return mgServerGroupDto;
    }

    @ApiOperation(value = "관리 서버그룹 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServerGroup", value = "모니터링 서버그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}", method = RequestMethod.PUT)
    public void updateServerGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestBody MgServerGroup mgServerGroup) {

        serverGroupService.updateServerGroup(monitoringGroupId, serverGroupId, mgServerGroup);
    }

    @ApiOperation(value = "관리 서버그룹 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupIds", value = "서버그룹 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.DELETE)
    public void deleteServerGroups(@PathVariable Integer monitoringGroupId, @RequestParam Integer[] serverGroupIds) {

        serverGroupService.deleteMgServerGroups(monitoringGroupId, serverGroupIds);
    }

    @ApiOperation(value = "관리 서버그룹 메트릭 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/metrics", method = RequestMethod.GET)
    public Collection<MgServerGroupCriticalValue> getServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        Collection<MgServerGroupCriticalValue> serverGroupMetrics = serverGroupService.getServerGroupMetrics(monitoringGroupId, serverGroupId);

        return serverGroupMetrics;
    }

    @ApiOperation(value = "관리 서버그룹 메트릭 임계치 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServerGroupCriticalValue", value = "모니터링 서버그룹 임계치", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @PathVariable Integer metricId, @RequestBody MgServerGroupCriticalValue mgServerGroupCriticalValue) {

        serverGroupService.updateServerGroupMetrics(monitoringGroupId, serverGroupId, metricId, mgServerGroupCriticalValue);
    }

    @ApiOperation(value = "관리 서버그룹 서버 리소스 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers" , method = RequestMethod.GET)
    public Collection<MgServer> getServerGroupServerResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        Collection<MgServer> mgServers = serverGroupService.getServerGroupServerResource(monitoringGroupId, serverGroupId);

        return mgServers;
    }

    @ApiOperation(value = "관리 서버그룹 서버 리소스 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceIds", value = "서버 리소스 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers" , method = RequestMethod.POST)
    public void insertServerGroupServerResources(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestBody Integer[] serverResourceIds) {

        serverGroupService.insertServerGroupServerResources(monitoringGroupId, serverGroupId, serverResourceIds);
    }

    @ApiOperation(value = "관리 서버그룹 서버 리소스 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceIds", value = "서버 리소스 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers" , method = RequestMethod.DELETE)
    public void deleteServerGroupServerResources(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestParam Integer[] serverResourceIds) {

        serverGroupService.deleteServerGroupServerResources(monitoringGroupId, serverGroupId, serverResourceIds);
    }

    @ApiOperation(value = "관리 서버그룹 서버 리소스 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverGroupId", value = "서버그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/servers/{serverResourceId}" , method = RequestMethod.DELETE)
    public void deleteServerGroupServerResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @PathVariable Integer serverResourceId) {

        serverGroupService.deleteServerGroupServerResource(monitoringGroupId, serverGroupId, serverResourceId);
    }
}
