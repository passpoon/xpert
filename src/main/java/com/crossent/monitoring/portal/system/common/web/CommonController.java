package com.crossent.monitoring.portal.system.common.web;

import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.system.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CommonController extends BaseController {

    @Autowired
    CommonService commonService;

    // 서버 유형 조회
    @RequestMapping(value = "/common/server-types", method = RequestMethod.GET)
    public Collection<ServerType> getServerTypes(@RequestParam Integer serverResourceId, @RequestParam String name) {

        Collection<ServerType> serverTypes = commonService.getServerTypes(serverResourceId, name);

        return serverTypes;
    }

    // 어플리케이션 정보 조회
    @RequestMapping(value = "/common/app-infos", method = RequestMethod.GET)
    public Collection<AppInfo> getAppInfos(@RequestParam Integer appResourceId, @RequestParam String name) {

        Collection<AppInfo> appInfos = commonService.getAppInfos(appResourceId, name);

        return appInfos;
    }

}
