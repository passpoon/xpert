package com.crossent.monitoring.portal.sample.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.jpa.domain.TypeCode;
import com.crossent.monitoring.portal.jpa.repository.MetricRepository;
import com.crossent.monitoring.portal.jpa.repository.TypeCodeRepository;
import com.crossent.monitoring.portal.sample.dto.MeasurementDto;
import com.crossent.monitoring.portal.jpa.repository.MeasurementJpaRepository;
import com.crossent.monitoring.portal.jpa.repository.MeasurementRepository;
import com.crossent.monitoring.portal.sample.dto.MetricDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


//@Service
public class MeasurementService {
    private static Logger logger = LoggerFactory.getLogger(MeasurementService.class);

    @Autowired
    MeasurementRepository measurmentRepository;

    @Autowired
    MetricRepository metricRepository;

    @Autowired
    TypeCodeRepository typeCodeRepository;

    @Autowired
    MeasurementJpaRepository measurementJpaRepository;


    public List<MeasurementDto> listMeasurement(){

        Iterable<Measurement> measurements = measurmentRepository.findAll();

        //logger.debug("measurements : {}", measurements);

        List<MeasurementDto> measurementList = new ArrayList<MeasurementDto>();

        Iterator<Measurement> iterator = measurements.iterator();

        while(iterator.hasNext()){
            Measurement m = iterator.next();
            //logger.debug("measurement : {}", m);
            MeasurementDto  measurementDto = new MeasurementDto();

            logger.debug("----------------------------------------------------------");
            logger.debug("id : {}, name : {}, description : {}", m.getId(), m.getName(), m.getDescription());
            measurementDto.setId(m.getId());
            measurementDto.setName(m.getName());
            measurementDto.setDescription(m.getDescription());
            Collection<Metric> metrics =  m.getMetrics();

            List<MetricDto> metricList = new ArrayList<MetricDto>();
            for(Metric metric : metrics){
                MetricDto metricDto = new MetricDto();
                metricDto.setId(metric.getId());
                metricDto.setName(metric.getName());
                metricDto.setDescription(metric.getDescription());
                metricDto.setType(metric.getTypeCode().getType());
                if(logger.isDebugEnabled()) {
                    logger.debug("Id :{} ", metric.getId());
                    logger.debug("Name :{} ", metric.getName());
                    logger.debug("Description :{} ", metric.getDescription());
                    logger.debug("Type :{} ", metric.getTypeCode().getType());
                }
                metricList.add(metricDto);

            }
            measurementDto.setMetrics(metricList);
            measurementList.add(measurementDto);
        }
        return measurementList;
    }

    public MeasurementDto selectMeasurement(int measurementId){

        Measurement measurement = measurmentRepository.findOne(measurementId);

        MeasurementDto outDto = new MeasurementDto();

        outDto.setId(measurement.getId());
        outDto.setName(measurement.getName());
        outDto.setDescription(measurement.getDescription());

        List<MetricDto> outMetrics = new ArrayList<MetricDto>();
        Collection<Metric> metrics = measurement.getMetrics();
        for(Metric metric : metrics){
            MetricDto outMetricDto = new MetricDto();
            outMetricDto.setId(metric.getId());
            outMetricDto.setName(metric.getName());
//            outMetricDto.setField(metric.getTypeCode().getField());
            outMetricDto.setDescription(metric.getDescription());
            outMetrics.add(outMetricDto);
        }
        outDto.setMetrics(outMetrics);
        return outDto;
    }


    public PagingResVo<Measurement> pagingMeasurement(PagingReqVo pagingReqVo, SearchReqVo searchReqVo){
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name");

        Page<Measurement> outPage = measurementJpaRepository.findAll(pagingReqVo.toPagingRequest());

        PagingResVo<Measurement> resPage = new PagingResVo<Measurement>(outPage, true);
        String name = "cp";
        Page<Measurement> page = measurementJpaRepository.findByNameLike(pagingReqVo.toPagingRequest(),"%"+name+"%");
        logger.debug("page ::: {}", page.getContent());

        return resPage;
    }



    public void insertMeasurement(MeasurementDto inDto){
        Measurement measurement = new Measurement();
        measurement.setName(inDto.getName());
        measurement.setDescription(inDto.getDescription());

        Measurement resMeasurement = measurmentRepository.save(measurement);

        logger.debug("resMeasurement : {}", resMeasurement);



        List<MetricDto> inMetrics =  inDto.getMetrics();
        if(inMetrics != null) {

            for (MetricDto dto : inDto.getMetrics()) {
                Metric m = new Metric();

                m.setName(dto.getName());
                //m.setMeasurementId(resMeasurement.getId());
                m.setMeasurement(resMeasurement);

                m.setDescription(dto.getDescription());
                //m.setMetricTypeCode(dto.getMetricTypeCode());
                TypeCode typeCode = typeCodeRepository.findOne(dto.getMetricTypeCode());
                logger.debug("typeCode : {}", typeCode);
                m.setTypeCode(typeCode);
                logger.debug("metric : {}", m);
                Metric resMetric = metricRepository.save(m);
            }
        }





    }



}
