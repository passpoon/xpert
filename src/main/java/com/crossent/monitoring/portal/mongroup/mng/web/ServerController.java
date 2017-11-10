package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerDto;
import com.crossent.monitoring.portal.mongroup.mng.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ServerController extends BaseController {

    @Autowired
    ServerService serverService;

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers", method = RequestMethod.GET)
    public PagingResVo pagingServer(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgServerDto> resPage = serverService.pagingServer(monitoringGroupId, paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}", method = RequestMethod.PUT)
    public void updateServer(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @RequestBody MgServer mgServer) {

        serverService.updateServer(monitoringGroupId, serverResourceId, mgServer);

    }

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics", method = RequestMethod.GET)
    public Collection<MgServerCriticalValue> getServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId) {

        Collection<MgServerCriticalValue> serverMetrics = serverService.getServerMetrics(monitoringGroupId, serverResourceId);

        return serverMetrics;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @PathVariable Integer metricId, @RequestBody MgServerCriticalValue mgServerCriticalValue) {

        serverService.updateServerMetrics(monitoringGroupId, serverResourceId, metricId, mgServerCriticalValue);
    }
}
