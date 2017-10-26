package com.crossent.monitoring.portal.system.common.web;

import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.system.common.service.MonGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class MonGroupController extends BaseController {
    Logger logger = LoggerFactory.getLogger(MonGroupService.class);

    @Autowired
    MonGroupService monGroupService;

    @RequestMapping(value = "/system/common/monitoring-groups", method = RequestMethod.GET,
                    params={"userId"})
    public Collection<MonGroup> listMonGroup(@RequestParam(name = "userId") String userId) {

        Collection<MonGroup> monGroup = monGroupService.listMonGroup(userId);
        return monGroup;
    }

    @RequestMapping(value="/system/common/user", method = RequestMethod.GET)
    public User listUser(@RequestParam(name = "uuid") String uuid) {

        User user = monGroupService.listUsers(uuid);

        return user;
    }

}
