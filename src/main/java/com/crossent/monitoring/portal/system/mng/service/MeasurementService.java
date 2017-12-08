package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.jpa.domain.StateCode;
import com.crossent.monitoring.portal.jpa.domain.TypeCode;
import com.crossent.monitoring.portal.jpa.repository.MeasurementRepository;
import com.crossent.monitoring.portal.jpa.repository.MetricRepository;
import com.crossent.monitoring.portal.jpa.repository.StateCodeRepository;
import com.crossent.monitoring.portal.jpa.repository.TypeCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MeasurementService {
    Logger logger = LoggerFactory.getLogger(MeasurementService.class);

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    TypeCodeRepository typeCodeRepository;

    @Autowired
    MetricRepository metricRepository;

    public PagingResVo<Measurement> pagingMeasurement(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if(keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<Measurement> measurements = null;
        if(key == null){
            //TODO 전체조회
            measurements = measurementRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "name":
                {
                    measurements = measurementRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description":
                {
                    measurements = measurementRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        PagingResVo<Measurement> resPage = new PagingResVo<Measurement>(measurements, true);

        return resPage;
    }

    public void insertMeasurement(Measurement inDto){
        Measurement measurement = new Measurement();
        measurement.setName(inDto.getName());
        measurement.setDescription(inDto.getDescription());

        Measurement resMeasurement = measurementRepository.save(measurement);
    }

    public void deleteMeasurements(Integer[] measurementIds) {

        metricRepository.deleteByMeasurement_IdIn(measurementIds);
        measurementRepository.deleteByIdIn(measurementIds);
    }

    public Measurement getMeasurement(Integer measurementId) {

        Measurement measurement = measurementRepository.findOne(measurementId);

        Measurement out = new Measurement();
        out.setId(measurement.getId());
        out.setName(measurement.getName());
        out.setDescription(measurement.getDescription());

        return out;
    }

    public Measurement updateUMeasurement(Integer measurementId, Measurement measurement) {

        Measurement getData = measurementRepository.findOne(measurementId);

        if(getData == null) {
            return null;
        }
        getData.setName(measurement.getName());
        getData.setDescription(measurement.getDescription());

        Measurement updateData = measurementRepository.save(getData);

        return updateData;
    }

    public void deleteMeasurement(Integer measurementId) {

        measurementRepository.delete(measurementId);
    }

    public Collection<Metric> getMeasurementMetrics(Integer measurementId){

        Measurement measurement = measurementRepository.findById(measurementId);
        Collection<Metric> metrics = measurement.getMetrics();

        return metrics;
    }

    public void insertMeasurementMetric(Integer measurementId, Metric metric){

//        Measurement measurement = measurementRepository.findById(measurementId);   Metric domain에서 PK 중복 피하기 위해 insertable = false, updatable = false을 설정함
//        TypeCode typeCode = typeCodeRepository.findOne(metric.getMetricTypeCode());  각각 설정한 것들을 다시 조회해와서 사용해오는 경우가 생겨 PK가 아닐경우는 Domain에서 조회하도록 셋팅할 것

        Metric metricDomain = new Metric();
        metricDomain.setName(metric.getName());
        metricDomain.setDescription(metric.getDescription());
//        metricDomain.setMeasurement(measurement);
//        metricDomain.setTypeCode(typeCode);
        //metric.setMeasurement(measurement);
        metricDomain.setMeasurementId(measurementId);
        metricDomain.setMetricTypeCode(metric.getMetricTypeCode());
        metricDomain.setFuncTypeCode(metric.getFuncTypeCode());
        metricDomain.setUnit(metric.getUnit());
        metricRepository.save(metricDomain);
    }

    public Metric updateMeasurementMetic(Integer measurementId, Integer metricId, Metric metric) {

        /*Measurement byIdAndMetrics_id = measurementRepository.findByIdAndMetrics_Id(measurementId, metricId);

        if (byIdAndMetrics_id == null) {
            return null;
        }
        byIdAndMetrics_id.setName(metric.getName());
        byIdAndMetrics_id.setDescription(metric.getDescription());
//        byIdAndMetrics_id.

        Measurement updateData = measurementRepository.save(byIdAndMetrics_id);

        return updateData;*/

        Metric getData = metricRepository.findOne(metricId);

        if (getData == null) {
            return null;
        }
        getData.setName(metric.getName());
        getData.setDescription(metric.getDescription());

        Metric updateData = metricRepository.save(getData);

        return updateData;
    }

    public void deleteMeasurementMetrics(Integer measurementId, Integer[] metricIds) {

        metricRepository.deleteByMeasurementIdAndIdIn(measurementId, metricIds);
    }

    public void deleteMeasurementMetric(Integer measurementId, Integer metricId) {

        metricRepository.deleteByMeasurementIdAndId(measurementId, metricId);
    }

    public Collection<TypeCode> getTypeCodes() {

        String code = "00001%";

        return typeCodeRepository.findAllByCodeLike(code);
    }

    public Collection<TypeCode> getFunTypeCodes() {

        String code = "00005%";

        return typeCodeRepository.findAllByCodeLike(code);
    }
}
