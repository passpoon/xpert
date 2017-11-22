package com.crossent.monitoring.portal.mongroup.moniotring.web;

import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonServerController extends BaseController {

    @Autowired
    MonServerService serverService;

    /*@RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses", method = RequestMethod.GET)
    public void getServerStatuses(@PathVariable Integer monitoringGroupId, @RequestParam Integer serverResourceId) {

        List<MgServer> serverDtos = serverService.getServerStatuses(monitoringGroupId);

        return serverDtos;
    }*/
}
