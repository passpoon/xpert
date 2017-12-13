package com.crossent.monitoring.portal.mongroup.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeCriticalValue;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerDto;
import com.crossent.monitoring.portal.mongroup.mng.service.ServerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class ServerController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    ServerService serverService;

    @ApiOperation(value = "관리 서버 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers", method = RequestMethod.GET)
    public PagingResVo pagingMonServer(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }
        PagingResVo<MgServerDto> resPage = serverService.pagingServer(monitoringGroupId, paging, search);
        if(logger.isDebugEnabled()) {
            logger.debug("resPage : {}", resPage);
        }
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
    public void updateMonServer(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @RequestBody MgServer mgServer) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
            logger.debug("mgServer : {}", mgServer);
        }
        serverService.updateServer(monitoringGroupId, serverResourceId, mgServer);

    }

    @ApiOperation(value = "관리 서버 메저먼트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/measurements", method = RequestMethod.GET)
    public Collection<Measurement> getMonServerMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
        }
        Collection<Measurement> measurements = serverService.getMonServerMeasurements(monitoringGroupId, serverResourceId);
        if(logger.isDebugEnabled()) {
            logger.debug("measurements : {}", measurements);
        }
        return measurements;
    }

    @ApiOperation(value = "관리 서버 메저먼트 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/measurements" , method = RequestMethod.POST)
    public void insertMonServerMeasurement(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @RequestBody Integer[] measurementIds){

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
            logger.debug("measurementIds : {}", Arrays.toString(measurementIds));
        }
        serverService.insertMonServerMeasurement(monitoringGroupId, serverResourceId, measurementIds);
    }

    @ApiOperation(value = "관리 서버 메저먼트 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/measurements" , method = RequestMethod.DELETE)
    public void deleteMonServerMeasurements(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @RequestParam Integer[] measurementIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
            logger.debug("measurementIds : {}", Arrays.toString(measurementIds));
        }
        serverService.deleteMonServerMeasurements(monitoringGroupId, serverResourceId, measurementIds);
    }

    @ApiOperation(value = "관리 서버 임계치 설정을 위한 메트릭 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics", method = RequestMethod.GET)
    public Collection<MgServerCriticalValue> getMonServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
        }
        Collection<MgServerCriticalValue> serverMetrics = serverService.getMonServerMetrics(monitoringGroupId, serverResourceId);
        if(logger.isDebugEnabled()) {
            logger.debug("serverMetrics : {}", serverMetrics);
        }
        return serverMetrics;
    }

    @ApiOperation(value = "관리 서버 메트릭 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId : {}", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId : {}", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds : {}", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics" , method = RequestMethod.POST)
    public void insertMonServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @RequestBody Integer[] metricIds){

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
            logger.debug("metricIds : {}", Arrays.toString(metricIds));
        }
        serverService.insertMonServerMetrics(monitoringGroupId, serverResourceId, metricIds);
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
    public void updateMonServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @PathVariable Integer metricId, @RequestBody MgServerCriticalValue mgServerCriticalValue) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
            logger.debug("metricId : {}", metricId);
            logger.debug("mgServerCriticalValue : {}", mgServerCriticalValue);
        }
        serverService.updateMonServerMetrics(monitoringGroupId, serverResourceId, metricId, mgServerCriticalValue);
    }

    @ApiOperation(value = "관리 서버 메트릭 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/management/server/servers/{serverResourceId}/metrics" , method = RequestMethod.DELETE)
    public void deleteMonServerMetrics(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @RequestParam Integer[] metricIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
            logger.debug("metricIds : {}", Arrays.toString(metricIds));
        }
        serverService.deleteMonServerMetrics(monitoringGroupId, serverResourceId, metricIds);
    }
}
