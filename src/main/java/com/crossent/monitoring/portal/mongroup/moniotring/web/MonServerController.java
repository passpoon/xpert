package com.crossent.monitoring.portal.mongroup.moniotring.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class MonServerController extends BaseController {

    @Autowired
    MonServerService serverService;

    /*@RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses", method = RequestMethod.GET)
    public Collection<MgServer> pagingServerStatuses(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        serverService.pagingServerStatuses(monitoringGroupId, paging, search);

        return resPage;
    }*/

    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses", method = RequestMethod.GET)
    public Collection<MgServer> getServerStatuses(@PathVariable Integer monitoringGroupId) {

        Collection<MgServer> serverStatuses = serverService.getServerStatuses(monitoringGroupId);

        /*Collection<Measurement> measurements = serverService.getServerStatuses(monitoringGroupId);*/
        return serverStatuses;
    }

}
