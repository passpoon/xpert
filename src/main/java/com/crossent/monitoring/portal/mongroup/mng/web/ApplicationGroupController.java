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
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ApplicationGroupController extends BaseController {

    @Autowired
    ApplicationGroupService applicationGroupService;

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups", method = RequestMethod.GET)
    public PagingResVo pagingAppGroup(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgAppGroupDto> resPage = applicationGroupService.pagingAppGroup(monitoringGroupId, paging, search);

        return resPage;
    }

   @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups", method = RequestMethod.POST)
    public void createAppGroup(@PathVariable Integer monitoringGroupId, @RequestBody MgAppGroup mgAppGroup) {

       applicationGroupService.createAppGroup(monitoringGroupId, mgAppGroup);
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}", method = RequestMethod.PUT)
    public void updateAppGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestBody MgAppGroup mgAppGroup) {

        applicationGroupService.updateAppGroup(monitoringGroupId, appGroupId, mgAppGroup);
    }

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/metrics", method = RequestMethod.GET)
    public Collection<MgAppGroupCriticalValue> getServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId) {

        Collection<MgAppGroupCriticalValue> appGroupMetrics = applicationGroupService.getAppGroupMetrics(monitoringGroupId, appGroupId);

        return appGroupMetrics;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateAppGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @PathVariable Integer metricId, @RequestBody MgAppGroupCriticalValue mgAppGroupCriticalValue) {

        applicationGroupService.updateAppGroupMetrics(monitoringGroupId, appGroupId, metricId, mgAppGroupCriticalValue);
    }

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/apps" , method = RequestMethod.GET)
    public Collection<MgApp> getAppGroupAppResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId) {

        Collection<MgApp> mgApps = applicationGroupService.getAppGroupAppResource(monitoringGroupId, appGroupId);

        return mgApps;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/apps" , method = RequestMethod.POST)
    public void insertAppGroupAppResources(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @RequestBody Integer[] appResourceIds) {

        applicationGroupService.insertAppGroupAppResources(monitoringGroupId, appGroupId, appResourceIds);
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app-group/app-groups/{appGroupId}/apps/{appResourceId}" , method = RequestMethod.DELETE)
    public void deleteAppGroupAppResource(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @PathVariable Integer appResourceId) {

        applicationGroupService.deleteAppGroupAppResource(monitoringGroupId, appGroupId, appResourceId);
    }
}
