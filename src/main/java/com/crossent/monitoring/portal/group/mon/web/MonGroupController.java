package com.crossent.monitoring.portal.group.mon.web;

import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.crossent.monitoring.portal.group.mon.service.MonGroupService;

import java.util.Collection;

@RestController
public class MonGroupController extends BaseController {

    @Autowired
    MonGroupService monGroupService;

    @RequestMapping(value = "/monitoring-groups/{userId}", method = RequestMethod.GET)
    public Collection<MonGroup> selectMonGroup(@PathVariable String userId) {

        Collection<MonGroup> monGroup = monGroupService.listMonGroup(userId);

        return monGroup;
    }
}
