package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroup;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupCriticalValue;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerGroupDto;
import com.crossent.monitoring.portal.mongroup.mng.service.ServerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ServerGroupController extends BaseController {

    @Autowired
    ServerGroupService serverGroupService;

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.GET)
    public PagingResVo pagingServerGroup(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgServerGroupDto> resPage = serverGroupService.pagingServerGroup(monitoringGroupId, paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups", method = RequestMethod.POST)
    public void createServerGroup(@PathVariable Integer monitoringGroupId, @RequestBody MgServerGroup mgServerGroup) {

        serverGroupService.createServerGroup(monitoringGroupId, mgServerGroup);
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}", method = RequestMethod.PUT)
    public void updateServerGroup(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId, @RequestBody MgServerGroup mgServerGroup) {

        serverGroupService.updateServerGroup(monitoringGroupId, serverGroupId, mgServerGroup);

    }

    /*@RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server-group/server-groups/{serverGroupId}/metrics", method = RequestMethod.GET)
    public Collection<MgServerGroupCriticalValue> getServerGroupMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverGroupId) {

        Collection<MgServerGroupCriticalValue> serverGroupMetrics = serverGroupService.getServerGroupMetrics(monitoringGroupId, serverGroupId);

        return serverGroupMetrics;
    }*/

    /*@Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @PathVariable Integer metricId, @RequestBody MgServerCriticalValue mgServerCriticalValue) {

        serverService.updateServerMetrics(monitoringGroupId, serverResourceId, metricId, mgServerCriticalValue);
    }*/
}
