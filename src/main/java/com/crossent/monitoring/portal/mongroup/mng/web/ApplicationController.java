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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class ApplicationController extends BaseController {

    @Autowired
    ApplicationService applicationService;


    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps", method = RequestMethod.GET)
    public PagingResVo pagingServer(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgAppDto> resPage = applicationService.pagingServer(monitoringGroupId, paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}", method = RequestMethod.PUT)
    public void updateServer(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @RequestBody MgApp mgApp) {

        applicationService.updateApp(monitoringGroupId, appResourceId, mgApp);

    }

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/app/apps/{appResourceId}/metrics", method = RequestMethod.GET)
    public MgAppCriticalValue getServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId) {

        MgAppCriticalValue appMetrics = applicationService.getAppMetrics(monitoringGroupId, appResourceId);

        return appMetrics;
    }

    /*@Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceID, @PathVariable Integer metricId, @RequestBody MgServerCriticalValue mgServerCriticalValue) {

        serverService.updateServerMetrics(monitoringGroupId, serverResourceID, metricId, mgServerCriticalValue);
    }*/
}
