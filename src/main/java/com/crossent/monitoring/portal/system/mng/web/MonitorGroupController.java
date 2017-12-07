package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.system.mng.dto.MgUserDto;
import com.crossent.monitoring.portal.system.mng.service.MonitorGroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class MonitorGroupController extends BaseController {

    @Autowired
    MonitorGroupService monitorGroupService;

    @ApiOperation(value = "시스템 관리 모니터링 그룹 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/monitoring-groups", method = RequestMethod.GET)
    public PagingResVo pagingMonGroup(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MonGroup> resPage = monitorGroupService.pagingMonGroup(paging, search);

        return resPage;
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monGroup", value = "모니터링 그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups", method = RequestMethod.POST)
    public void insertMonGroup(@RequestBody MonGroup monGroup) {

        monitorGroupService.insertMonGroup(monGroup);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupIds", value = "모니터링 그룹 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups", method = RequestMethod.DELETE)
    public void deleteMonGroups(@RequestParam Integer[] monitoringGroupIds) {

        monitorGroupService.deleteMonGroups(monitoringGroupIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}", method = RequestMethod.GET)
    public MonGroup getMonGroup(@PathVariable Integer monitoringGroupId) {

        MonGroup monGroup = monitorGroupService.getMonGroup(monitoringGroupId);

        return monGroup;
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "monGroup", value = "모니터링 그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}", method = RequestMethod.PUT)
    public void updateMonGroup(@PathVariable Integer monitoringGroupId, @RequestBody MonGroup monGroup) {

        monitorGroupService.updateMonGroup(monitoringGroupId, monGroup);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}", method = RequestMethod.DELETE)
    public void deleteMonGroup(@PathVariable Integer monitoringGroupId) {

        monitorGroupService.deleteMonGroup(monitoringGroupId);
    }

    // 서버
    @ApiOperation(value = "시스템 관리 모니터링 그룹 서버 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers", method = RequestMethod.GET)
    public Collection<ServerResource> getMonGroupServers(@PathVariable Integer monitoringGroupId) {

        Collection<ServerResource> serverResources = monitorGroupService.getMonGroupServers(monitoringGroupId);

        return serverResources;
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 서버 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceIds", value = "서버 리소스 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional // 다중 서버 추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers", method = RequestMethod.POST)
    public void insertMonGroupServers(@PathVariable Integer monitoringGroupId, @RequestBody Integer[] serverResourceIds) {

        monitorGroupService.insertMonGroupServers(monitoringGroupId, serverResourceIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 서버 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceIds", value = "서버 리소스 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers" , method = RequestMethod.DELETE)
    public void deleteMonGroupServers(@PathVariable Integer monitoringGroupId, @RequestParam Integer[] serverResourceIds) {

        monitorGroupService.deleteMonGroupServers(monitoringGroupId, serverResourceIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 서버 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers/{serverResourceId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupServer(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId) {

        monitorGroupService.deleteMonGroupServer(monitoringGroupId, serverResourceId);
    }

    /*@RequestMapping(value = "/system/management/monitoring-groups/servers", method = RequestMethod.GET)
    public Collection<ServerResource> getServers(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<ServerResource> serverResources = monitorGroupService.getServers(monitoringGroupId, name);

        return serverResources;
    }*/

    // 어플리케이션
    @ApiOperation(value = "시스템 관리 모니터링 그룹 어플리케이션 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps", method = RequestMethod.GET)
    public Collection<AppResource> getMonGroupApps(@PathVariable Integer monitoringGroupId) {

        Collection<AppResource> appResources = monitorGroupService.getMonGroupApps(monitoringGroupId);

        return appResources;
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 어플리케이션 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceIds", value = "어플리케이션 리소스 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional // 다중 어플리케이션 추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps", method = RequestMethod.POST)
    public void insertMonGroupApps(@PathVariable Integer monitoringGroupId, @RequestBody Integer[] appResourceIds) {

        monitorGroupService.insertMonGroupApps(monitoringGroupId, appResourceIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 어플리케이션 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceIds", value = "어플리케이션 리소스 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps" , method = RequestMethod.DELETE)
    public void deleteMonGroupApps(@PathVariable Integer monitoringGroupId, @RequestParam Integer[] appResourceIds) {

        monitorGroupService.deleteMonGroupApps(monitoringGroupId, appResourceIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 서버 어플리케이션 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps/{appResourceId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupApp(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId) {

        monitorGroupService.deleteMonGroupApp(monitoringGroupId, appResourceId);
    }

    /*@RequestMapping(value = "/system/management/monitoring-groups/apps", method = RequestMethod.GET)
    public Collection<AppResource> getApps(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<AppResource> appResources = monitorGroupService.getApps(monitoringGroupId, name);

        return appResources;
    }*/

    // 관리자
    @ApiOperation(value = "시스템 관리 모니터링 그룹 관리자 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers", method = RequestMethod.GET)
    public Collection<MgUserDto> getMonGroupManagers(@PathVariable Integer monitoringGroupId) {

        Collection<MgUserDto> mgUserDto = monitorGroupService.getMonGroupManagers(monitoringGroupId);

        return mgUserDto;
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 관리자 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "userIds", value = "사용자 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional // 다중 관리자추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers", method = RequestMethod.POST)
    public void insertMonGroupManagers(@PathVariable Integer monitoringGroupId, @RequestBody String[] userIds) {

        monitorGroupService.insertMonGroupManagers(monitoringGroupId, userIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 관리자 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "userIds", value = "사용자 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers" , method = RequestMethod.DELETE)
    public void deleteMonGroupManagers(@PathVariable Integer monitoringGroupId, @RequestParam String[] userIds) {

        monitorGroupService.deleteMonGroupManagers(monitoringGroupId, userIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 관리자 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "사용자 ID", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers/{userId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupManager(@PathVariable Integer monitoringGroupId, @PathVariable String userId) {

        monitorGroupService.deleteMonGroupManager(monitoringGroupId, userId);
    }

    // 운영자
    @ApiOperation(value = "시스템 관리 모니터링 그룹 운영자 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators", method = RequestMethod.GET)
    public Collection<MgUserDto> getMonGroupOperators(@PathVariable Integer monitoringGroupId) {

        Collection<MgUserDto> mgUserDto = monitorGroupService.getMonGroupOperators(monitoringGroupId);

        return mgUserDto;
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 운영자 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "userIds", value = "사용자 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional // 다중 운영자추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators", method = RequestMethod.POST)
    public void insertMonGroupOperators(@PathVariable Integer monitoringGroupId, @RequestBody String[] userIds) {

        monitorGroupService.insertMonGroupOperators(monitoringGroupId, userIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 운영자 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "userIds", value = "사용자 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators" , method = RequestMethod.DELETE)
    public void deleteMonGroupOperators(@PathVariable Integer monitoringGroupId, @RequestParam String[] userIds) {

        monitorGroupService.deleteMonGroupOperators(monitoringGroupId, userIds);
    }

    @ApiOperation(value = "시스템 관리 모니터링 그룹 운영자 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "사용자 ID", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators/{userId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupOperator(@PathVariable Integer monitoringGroupId, @PathVariable String userId) {

        monitorGroupService.deleteMonGroupOperator(monitoringGroupId, userId);
    }

    /*// 모니터링 그룹(관리자 조회)
    @RequestMapping(value = "/system/common/monitoring-groups/managers", method = RequestMethod.GET)
    public Collection<User> getMonGroupManagers(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<User> managers = monitorGroupService.getMonGroupUsers(monitoringGroupId, name);

        return managers;
    }

    // 모니터링 그룹(운영자 조회)
    @RequestMapping(value = "/system/common/monitoring-groups/operators", method = RequestMethod.GET)
    public Collection<User> getMonGroupOperators(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<User> operators = monitorGroupService.getMonGroupUsers(monitoringGroupId, name);

        return operators;
    }*/
}
