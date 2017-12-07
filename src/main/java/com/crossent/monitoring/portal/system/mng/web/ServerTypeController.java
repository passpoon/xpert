package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeCriticalValue;
import com.crossent.monitoring.portal.system.mng.dto.MeasurementDto;
import com.crossent.monitoring.portal.system.mng.dto.ServerTypeDto;
import com.crossent.monitoring.portal.system.mng.service.ServerTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ServerTypeController extends BaseController{

    @Autowired
    ServerTypeService serverTypeService;

    @ApiOperation(value = "시스템 관리 서버 유형 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/server-types", method = RequestMethod.GET)
    public PagingResVo pagingServerType(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<ServerTypeDto> resPage = serverTypeService.pagingServerType(paging, search);

        return resPage;
    }

    @ApiOperation(value = "시스템 관리 서버 유형 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverType", value = "서버 유형", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types", method = RequestMethod.POST)
    public void insertServerType(@RequestBody ServerType serverType) {

        serverTypeService.insertServerType(serverType);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeIds", value = "서버 유형 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types", method = RequestMethod.DELETE)
    public void deleteServerTypes(@RequestParam Integer[] serverTypeIds){

        serverTypeService.deleteServerTypes(serverTypeIds);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}", method = RequestMethod.GET)
    public ServerType getServerType(@PathVariable Integer serverTypeId) {

        ServerType getUser =  serverTypeService.getServerType(serverTypeId);

        return getUser;
    }

    @ApiOperation(value = "시스템 관리 서버 유형 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverType", value = "서버 유형", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}", method = RequestMethod.PUT)
    public void updateServerType(@PathVariable Integer serverTypeId, @RequestBody ServerType serverType) {

        serverTypeService.updateServerType(serverTypeId, serverType);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}", method = RequestMethod.DELETE)
    public void deleteServerType(@PathVariable Integer serverTypeId) {

        serverTypeService.deleteServerType(serverTypeId);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements", method = RequestMethod.GET)
    public Collection<Measurement> getServerTypeMeasurement(@PathVariable Integer serverTypeId) {

        Collection<Measurement> measurements = serverTypeService.getServerTypeMeasurement(serverTypeId);
        return measurements;
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements" , method = RequestMethod.POST)
    public void insertServerTypeMeasurement(@PathVariable Integer serverTypeId, @RequestBody Integer[] measurementIds){

        serverTypeService.insertServerTypeMeasurement(serverTypeId, measurementIds);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements" , method = RequestMethod.DELETE)
    public void deleteServerTypeMeasurements(@PathVariable Integer serverTypeId, @RequestParam Integer[] measurementIds) {

        serverTypeService.deleteServerTypeMeasurements(serverTypeId, measurementIds);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements/{measurementId}" , method = RequestMethod.DELETE)
    public void deleteServerTypeMeasurement(@PathVariable Integer serverTypeId, @PathVariable Integer measurementId) {

        serverTypeService.deleteServerTypeMeasurement(serverTypeId, measurementId);
    }

    /*@RequestMapping(value = "/system/management/server-types/{serverTypeId}/critical", method = RequestMethod.GET)
    public PagingResVo pagingServerTypeCritical(@PathVariable Integer serverTypeId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<MeasurementDto> resPage = serverTypeService.pagingServerTypeCritical(serverTypeId, paging, search);

        return resPage;
    }*/

    /*@RequestMapping(value = "/system/management/server-types/{serverTypeId}/critical", method = RequestMethod.GET)
    public Collection<MeasurementDto> getServerTypeCritical(@PathVariable Integer serverTypeId) {

        Collection<MeasurementDto> measurementDtos = serverTypeService.getServerTypeCritical(serverTypeId);

        return measurementDtos;
    }*/

    /*@RequestMapping(value = "/system/management/server-types//measurements/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<Metric> getServerTypeMeasurementMetrics(@PathVariable Integer measurementId) {

        Collection<Metric> metrics = serverTypeService.getServerTypeMeasurementMetrics(measurementId);

        return metrics;
    }*/

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 메트릭 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<ServerTypeCriticalValue> getServerTypeMeasurementMetrics(@PathVariable Integer serverTypeId, @PathVariable Integer measurementId) {

        Collection<ServerTypeCriticalValue> serverTypeCriticalValues = serverTypeService.getServerTypeMeasurementMetrics(serverTypeId, measurementId);

        return serverTypeCriticalValues;
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 메트릭 임계치 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverTypeCriticalValue", value = "서버 유형 메트릭 임계치", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements/{measurementId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateServerTypeCritical(@PathVariable Integer serverTypeId, @PathVariable Integer measurementId, @PathVariable Integer metricId, @RequestBody ServerTypeCriticalValue serverTypeCriticalValue) {

        serverTypeService.updateServerTypeCritical(serverTypeId, measurementId, metricId, serverTypeCriticalValue);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 메트릭 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements/{measurementId}" , method = RequestMethod.POST)
    public void insertServerTypeMeasurementMetrics(@PathVariable Integer serverTypeId, @PathVariable Integer measurementId, @RequestBody Integer[] metricIds){

        serverTypeService.insertServerTypeMeasurementMetrics(serverTypeId, measurementId, metricIds);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 메트릭 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements/{measurementId}/metrics" , method = RequestMethod.DELETE)
    public void deleteServerTypeMeasurementsMetrics(@PathVariable Integer serverTypeId, @PathVariable Integer measurementId, @RequestParam Integer[] metricIds) {

        serverTypeService.deleteServerTypeMeasurementsMetrics(serverTypeId, measurementId, metricIds);
    }

    @ApiOperation(value = "시스템 관리 서버 유형 메저먼트 메트릭 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverTypeId", value = "서버 유형 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/measurements/{measurementId}/metrics/{metricId}" , method = RequestMethod.DELETE)
    public void deleteServerTypeMeasurementsMetric(@PathVariable Integer serverTypeId, @PathVariable Integer measurementId, @PathVariable Integer metricId) {

        serverTypeService.deleteServerTypeMeasurementsMetric(serverTypeId, measurementId, metricId);
    }
}
