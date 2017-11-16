package com.crossent.monitoring.portal.common.web;

import com.crossent.monitoring.portal.common.service.CommonService;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public class CommonController {

    @Autowired
    CommonService commonService;

    // *시스템 관리*


    /*// 모니터링 그룹(관리자 조회)   type_code에 따라 분류 되어야함 현재는 name으로만 조회해오고 있음
    @RequestMapping(value = "/system/common/monitoring-groups/managers", method = RequestMethod.GET)
    public Collection<User> getMonGroupManagers(@RequestParam Integer monitoringGroupId, @RequestParam String name) {

        Collection<User> managers = monGroupService.getMonGroupUsers(monitoringGroupId, name);

        return managers;
    }*/

    // 모니터링 그룹(운영자 조회)
    //@RequestMapping(value = "/system/common/monitoring-groups/operators", method = RequestMethod.GET)

    /*// 서버유형 조회
    @RequestMapping(value = "/system/common/server-types", method = RequestMethod.GET)
    public Collection<ServerType> getServerResourceServerTypes(@RequestParam Integer serverId, @RequestParam String name) {

        Collection<ServerType> serverTypes = commonService.getServerResourceServerTypes(serverId, name);

        return serverTypes;
    }*/
}
