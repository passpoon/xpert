package com.crossent.monitoring.portal.system.common.web;

import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.system.common.service.CommonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CommonController extends BaseController {

    @Autowired
    CommonService commonService;

    /*// 서버 유형 조회
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
    }*/

    // 메저먼트 메트릭 조회
    @ApiOperation(value = "공통 메저먼트 메트릭 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/common/measurement/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<Metric> geteMeasurementMetrics(@PathVariable Integer measurementId) {

        Collection<Metric> metrics = commonService.getMeasurementMetrics(measurementId);

        return metrics;
    }

    // 서버 유형 조회
    @ApiOperation(value = "공통 서버 유형 조회")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/common/server-types", method = RequestMethod.GET)
    public Collection<ServerType> getServerTypes() {

        Collection<ServerType> serverTypes = commonService.getServerTypes();

        return serverTypes;
    }

    // 어플리케이션 정보 조회
    @ApiOperation(value = "공통 어플리케이션 정보 조회")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/common/app-infos", method = RequestMethod.GET)
    public Collection<AppInfo> getAppInfos() {

        Collection<AppInfo> appInfos = commonService.getAppInfos();

        return appInfos;
    }
}
