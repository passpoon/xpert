package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.domain.User;
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

}
