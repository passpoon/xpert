package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.system.mng.dto.MgUserDto;
import com.crossent.monitoring.portal.system.mng.service.MonitorGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class MonitorGroupController extends BaseController {

    @Autowired
    MonitorGroupService monitorGroupService;

    @RequestMapping(value = "/system/management/monitoring-groups", method = RequestMethod.GET)
    public PagingResVo pagingMonGroup(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MonGroup> resPage = monitorGroupService.pagingMonGroup(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups", method = RequestMethod.POST)
    public void insertMonGroup(@RequestBody MonGroup monGroup) {

        monitorGroupService.insertMonGroup(monGroup);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups", method = RequestMethod.DELETE)
    public void deleteMonGroups(@RequestParam Integer[] monitoringGroupId) {

        monitorGroupService.deleteMonGroups(monitoringGroupId);
    }

    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}", method = RequestMethod.GET)
    public MonGroup getMonGroup(@PathVariable Integer monitoringGroupId) {

        MonGroup monGroup = monitorGroupService.getMonGroup(monitoringGroupId);

        return monGroup;
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}", method = RequestMethod.PUT)
    public void updateMonGroup(@PathVariable Integer monitoringGroupId, @RequestBody MonGroup monGroup) {

        monitorGroupService.updateMonGroup(monitoringGroupId, monGroup);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}", method = RequestMethod.DELETE)
    public void deleteMonGroup(@PathVariable Integer monitoringGroupId) {

        monitorGroupService.deleteMonGroup(monitoringGroupId);
    }

    // 서버
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers", method = RequestMethod.GET)
    public Collection<ServerResource> getMonGroupServers(@PathVariable Integer monitoringGroupId) {

        Collection<ServerResource> serverResources = monitorGroupService.getMonGroupServers(monitoringGroupId);

        return serverResources;
    }

    @Transactional // 다중 서버 추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers", method = RequestMethod.POST)
    public void insertMonGroupServers(@PathVariable Integer monitoringGroupId, @RequestBody Integer[] serverResourceIds) {

        monitorGroupService.insertMonGroupServers(monitoringGroupId, serverResourceIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers" , method = RequestMethod.DELETE)
    public void deleteMonGroupServers(@PathVariable Integer monitoringGroupId, @RequestParam Integer[] serverResourceIds) {

        monitorGroupService.deleteMonGroupServers(monitoringGroupId, serverResourceIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/servers/{serverResourceId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupServer(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId) {

        monitorGroupService.deleteMonGroupServer(monitoringGroupId, serverResourceId);
    }

    @RequestMapping(value = "/system/management/monitoring-groups/servers", method = RequestMethod.GET)
    public Collection<ServerResource> getServers(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<ServerResource> serverResources = monitorGroupService.getServers(monitoringGroupId, name);

        return serverResources;
    }

    // 어플리케이션
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps", method = RequestMethod.GET)
    public Collection<AppResource> getMonGroupApps(@PathVariable Integer monitoringGroupId) {

        Collection<AppResource> appResources = monitorGroupService.getMonGroupApps(monitoringGroupId);

        return appResources;
    }

    @Transactional // 다중 어플리케이션 추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps", method = RequestMethod.POST)
    public void insertMonGroupApps(@PathVariable Integer monitoringGroupId, @RequestBody Integer[] appResourceIds) {

        monitorGroupService.insertMonGroupApps(monitoringGroupId, appResourceIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps" , method = RequestMethod.DELETE)
    public void deleteMonGroupApps(@PathVariable Integer monitoringGroupId, @RequestParam Integer[] appResourceIds) {

        monitorGroupService.deleteMonGroupApps(monitoringGroupId, appResourceIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/apps/{appResourceId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupApp(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId) {

        monitorGroupService.deleteMonGroupApp(monitoringGroupId, appResourceId);
    }

    @RequestMapping(value = "/system/management/monitoring-groups/apps", method = RequestMethod.GET)
    public Collection<AppResource> getApps(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<AppResource> appResources = monitorGroupService.getApps(monitoringGroupId, name);

        return appResources;
    }

    // 관리자
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers", method = RequestMethod.GET)
    public Collection<MgUserDto> getMonGroupManagers(@PathVariable Integer monitoringGroupId) {

        Collection<MgUserDto> mgUserDto = monitorGroupService.getMonGroupManagers(monitoringGroupId);

        return mgUserDto;
    }

    @Transactional // 다중 관리자추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers", method = RequestMethod.POST)
    public void insertMonGroupManagers(@PathVariable Integer monitoringGroupId, @RequestBody String[] userIds) {

        monitorGroupService.insertMonGroupManagers(monitoringGroupId, userIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers" , method = RequestMethod.DELETE)
    public void deleteMonGroupManagers(@PathVariable Integer monitoringGroupId, @RequestParam String[] userIds) {

        monitorGroupService.deleteMonGroupManagers(monitoringGroupId, userIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/managers/{userId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupManager(@PathVariable Integer monitoringGroupId, @PathVariable String userId) {

        monitorGroupService.deleteMonGroupManager(monitoringGroupId, userId);
    }

    // 운영자
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators", method = RequestMethod.GET)
    public Collection<MgUserDto> getMonGroupOperators(@PathVariable Integer monitoringGroupId) {

        Collection<MgUserDto> mgUserDto = monitorGroupService.getMonGroupOperators(monitoringGroupId);

        return mgUserDto;
    }

    @Transactional // 다중 관리자추가
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators", method = RequestMethod.POST)
    public void insertMonGroupOperators(@PathVariable Integer monitoringGroupId, @RequestBody String[] userIds) {

        monitorGroupService.insertMonGroupOperators(monitoringGroupId, userIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators" , method = RequestMethod.DELETE)
    public void deleteMonGroupOperators(@PathVariable Integer monitoringGroupId, @RequestParam String[] userIds) {

        monitorGroupService.deleteMonGroupOperators(monitoringGroupId, userIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/monitoring-groups/{monitoringGroupId}/operators/{userId}" , method = RequestMethod.DELETE)
    public void deleteMonGroupOperator(@PathVariable Integer monitoringGroupId, @PathVariable String userId) {

        monitorGroupService.deleteMonGroupOperator(monitoringGroupId, userId);
    }

    // 모니터링 그룹(관리자 조회)   type_code에 따라 분류 되어야함 현재는 name으로만 조회해오고 있음
    @RequestMapping(value = "/system/common/monitoring-groups/managers", method = RequestMethod.GET)
    public Collection<User> getMonGroupManagers(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<User> managers = monitorGroupService.getMonGroupUsers(monitoringGroupId, name);

        return managers;
    }

    // 모니터링 그룹(운영자 조회)
    //@RequestMapping(value = "/system/common/monitoring-groups/operators", method = RequestMethod.GET)
}
