package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeCriticalValuePK;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface ServerTypeCriticalValueRepository extends CrudRepository<ServerTypeCriticalValue, ServerTypeCriticalValuePK> {

    public ServerTypeCriticalValue findByServerTypeIdAndMeasurementIdAndMetricId(Integer serverTypeId, Integer measurementId, Integer metricId);
    public Collection<ServerTypeCriticalValue> findAllByServerTypeIdAndMeasurementId(Integer serverTypeId, Integer measurementId);
    public void deleteByServerTypeIdAndMeasurementIdAndMetricIdIn(Integer serverTypeId, Integer measurementId, Integer[] metricIds);
    public void deleteByServerTypeIdAndMeasurementIdAndMetricId(Integer serverTypeId, Integer measurementId, Integer metricId);
}
