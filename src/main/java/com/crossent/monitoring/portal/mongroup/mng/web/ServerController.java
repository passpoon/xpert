package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerDto;
import com.crossent.monitoring.portal.mongroup.mng.service.ServerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ServerController extends BaseController {

    @Autowired
    ServerService serverService;

    @ApiOperation(value = "관리 서버 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers", method = RequestMethod.GET)
    public PagingResVo pagingServer(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MgServerDto> resPage = serverService.pagingServer(monitoringGroupId, paging, search);

        return resPage;
    }

    @ApiOperation(value = "관리 서버 정보 수정(대시보드, 모니터링 여부)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServer", value = "모니터링 서버", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}", method = RequestMethod.PUT)
    public void updateServer(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @RequestBody MgServer mgServer) {

        serverService.updateServer(monitoringGroupId, serverResourceId, mgServer);

    }

    @ApiOperation(value = "관리 서버 임계치 설정을 위한 메트릭 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics", method = RequestMethod.GET)
    public Collection<MgServerCriticalValue> getServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId) {

        Collection<MgServerCriticalValue> serverMetrics = serverService.getServerMetrics(monitoringGroupId, serverResourceId);

        return serverMetrics;
    }

    @ApiOperation(value = "관리 서버 메트릭별 임계치 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mgServerCriticalValue", value = "모니터링 서버 메트릭 임계치", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @PathVariable Integer metricId, @RequestBody MgServerCriticalValue mgServerCriticalValue) {

        serverService.updateServerMetrics(monitoringGroupId, serverResourceId, metricId, mgServerCriticalValue);
    }
}
