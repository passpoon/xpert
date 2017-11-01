package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.system.mng.dto.MeasurementDto;
import com.crossent.monitoring.portal.system.mng.dto.MetricDto;
import com.crossent.monitoring.portal.system.mng.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class MeasurementController  extends BaseController {

    @Autowired
    MeasurementService measurementService;

    @RequestMapping(value = "/system/management/measurements", method = RequestMethod.GET)
    public PagingResVo pagingMeasurement(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<Measurement> resPage = measurementService.pagingMeasurement(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/measurements", method = RequestMethod.POST)
    public void insertMeasurement(@RequestBody Measurement measurement) {

        measurementService.insertMeasurement(measurement);
    }

    @Transactional
    @RequestMapping(value = "/system/management/measurements", method = RequestMethod.DELETE)
    public void deleteMeasurements(@RequestParam Integer[] measurementIds) {

        measurementService.deleteMeasurements(measurementIds);
    }

    @RequestMapping(value = "/system/management/measurements/{measurementId}", method = RequestMethod.GET)
    public Measurement getMeasurement(@PathVariable Integer measurementId) {

        Measurement getMeasurement =  measurementService.getMeasurement(measurementId);

        return getMeasurement;
    }

    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}", method = RequestMethod.PUT)
    public void updateMeasurement(@PathVariable Integer measurementId, @RequestBody Measurement measurement){

        measurementService.updateUMeasurement(measurementId, measurement);
    }

    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}", method = RequestMethod.DELETE)
    public void deleteMeasurement(@PathVariable Integer measurementId){

        measurementService.deleteMeasurement(measurementId);
    }

    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics", method = RequestMethod.GET)
    public Collection<Metric> getMeasurementMetrics(@PathVariable Integer measurementId){

        Collection<Metric> metrics = measurementService.getMeasurementMetrics(measurementId);

        return metrics;
    }

    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics", method = RequestMethod.POST)
    public void insertMeasurementMetic(@PathVariable Integer measurementId, @RequestBody Metric metric) {

        measurementService.insertMeasurementMetric(measurementId, metric);
    }

    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics" , method = RequestMethod.DELETE)
    public void deleteMeasurementMetrics(@PathVariable Integer measurementId, @RequestParam  Integer[] metricIds) {

        measurementService.deleteMeasurementMetrics(measurementId, metricIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/measurements/{measurementId}/metrics/{metricId}" , method = RequestMethod.DELETE)
    public void deleteMeasurementMetrics(@PathVariable Integer measurementId, @PathVariable  Integer metricId) {

        measurementService.deleteMeasurementMetric(measurementId, metricId);
    }



}
