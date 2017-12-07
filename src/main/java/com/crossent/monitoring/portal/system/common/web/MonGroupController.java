package com.crossent.monitoring.portal.system.common.web;

import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.system.common.dto.UserDto;
import com.crossent.monitoring.portal.system.common.service.MonGroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MonGroupController extends BaseController {
    Logger logger = LoggerFactory.getLogger(MonGroupService.class);

    @Autowired
    MonGroupService monGroupService;

    @ApiOperation(value = "공통 모니터링 그룹 사용자 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 ID", required = true, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/common/monitoring-groups", method = RequestMethod.GET, params={"userId"})
    public Collection<MonGroup> listMonGroup(@RequestParam(name = "userId") String userId) {

        Collection<MonGroup> monGroup = monGroupService.listMonGroup(userId);

        return monGroup;
    }

    @ApiOperation(value = "공통 사용자 uuid 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "사용자 uuid", required = true, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/common/user", method = RequestMethod.GET)
    public UserDto getUser(@RequestParam(name = "uuid") String uuid) {

        UserDto user = monGroupService.listUsers(uuid);

        return user;
    }
}
