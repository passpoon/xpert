package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeCriticalValuePK;
import org.springframework.data.repository.CrudRepository;


public interface ServerTypeCriticalValueRepository extends CrudRepository<ServerTypeCriticalValue, ServerTypeCriticalValuePK> {

    public ServerTypeCriticalValue findAllByServerTypeIdAndMeasurementIdAndMetricId(Integer serverTypeId, Integer measurementId, Integer metricId);

}
