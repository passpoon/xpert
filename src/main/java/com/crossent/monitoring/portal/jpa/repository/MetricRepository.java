package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Metric;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface MetricRepository extends CrudRepository<Metric, Integer> {

    // foreign key 참조였을경우 measurementId 삭제
    public void deleteByMeasurement_IdIn(Integer[] measurementIds);
    //public void deleteByMeasurement_IdIn(Integer measurementIds,Integer[] metricIds);

//    public void deleteByIdIn(Integer[] metricIds);
    public void deleteByMeasurementIdAndIdIn(Integer measurementId,Integer[] metricIds);
    public void deleteByMeasurementIdAndId(Integer measurementId, Integer metricId);

//    public Collection<Metric> findAllByTypeCode(String code);
}
