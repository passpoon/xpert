package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.jpa.domain.StateCode;
import com.crossent.monitoring.portal.jpa.domain.TypeCode;
import com.crossent.monitoring.portal.system.mng.service.MeasurementService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class MeasurementController  extends BaseController {

    @Autowired
    MeasurementService measurementService;

    @ApiOperation(value = "시스템 관리 메저먼트 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/measurements", method = RequestMethod.GET)
    public PagingResVo pagingMeasurement(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<Measurement> resPage = measurementService.pagingMeasurement(paging, search);

        return resPage;
    }

    @ApiOperation(value = "시스템 관리 메저먼트 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurement", value = "메저먼트", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements", method = RequestMethod.POST)
    public void insertMeasurement(@RequestBody Measurement measurement) {

        measurementService.insertMeasurement(measurement);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementIds", value = "메저먼트 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements", method = RequestMethod.DELETE)
    public void deleteMeasurements(@RequestParam Integer[] measurementIds) {

        measurementService.deleteMeasurements(measurementIds);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/measurements/{measurementId}", method = RequestMethod.GET)
    public Measurement getMeasurement(@PathVariable Integer measurementId) {

        Measurement getMeasurement =  measurementService.getMeasurement(measurementId);

        return getMeasurement;
    }

    @ApiOperation(value = "시스템 관리 메저먼트 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "measurement", value = "메저먼트", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}", method = RequestMethod.PUT)
    public void updateMeasurement(@PathVariable Integer measurementId, @RequestBody Measurement measurement){

        measurementService.updateUMeasurement(measurementId, measurement);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}", method = RequestMethod.DELETE)
    public void deleteMeasurement(@PathVariable Integer measurementId){

        measurementService.deleteMeasurement(measurementId);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 메트릭 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<Metric> getMeasurementMetrics(@PathVariable Integer measurementId){

        Collection<Metric> metrics = measurementService.getMeasurementMetrics(measurementId);

        return metrics;
    }

    @ApiOperation(value = "시스템 관리 메저먼트 메트릭 다중 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metric", value = "메트릭", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics", method = RequestMethod.POST)
    public void insertMeasurementMetic(@PathVariable Integer measurementId, @RequestBody Metric metric) {

        measurementService.insertMeasurementMetric(measurementId, metric);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 메트릭 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metric", value = "메트릭", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics/{metricId}", method = RequestMethod.PUT)
    public void updateMeasurementMetic(@PathVariable Integer measurementId, @PathVariable Integer metricId, @RequestBody Metric metric) {

        measurementService.updateMeasurementMetic(measurementId, metricId, metric);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 메트릭 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricIds", value = "메트릭 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics" , method = RequestMethod.DELETE)
    public void deleteMeasurementMetrics(@PathVariable Integer measurementId, @RequestParam  Integer[] metricIds) {

        measurementService.deleteMeasurementMetrics(measurementId, metricIds);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 메트릭 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "measurementId", value = "메저먼트 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "metricId", value = "메트릭 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics/{metricId}" , method = RequestMethod.DELETE)
    public void deleteMeasurementMetrics(@PathVariable Integer measurementId, @PathVariable  Integer metricId) {

        measurementService.deleteMeasurementMetric(measurementId, metricId);
    }

    @ApiOperation(value = "시스템 관리 메저먼트 타입코드 조회")
    @RequestMapping(value = "/system/management/measurement/type-codes", method = RequestMethod.GET)
    public Collection<TypeCode> getTypeCodes() {

        Collection<TypeCode> typeCodes = measurementService.getTypeCodes();

        return typeCodes;
    }

    @ApiOperation(value = "시스템 관리 메저먼트 기능 상태코드 조회")
    @RequestMapping(value = "/system/management/measurement/fun-type-codes", method = RequestMethod.GET)
    public Collection<TypeCode> getFunTypeCodes() {

        Collection<TypeCode> typeCodes = measurementService.getFunTypeCodes();

        return typeCodes;
    }
}
