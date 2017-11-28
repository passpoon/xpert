package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValuePK;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface MgServerCriticalValueRepository extends CrudRepository<MgServerCriticalValue, MgServerCriticalValuePK> {

    public MgServerCriticalValue findByMonGroupIdAndServerResourceId(Integer monGroupId, Integer serverResourceId);

    public MgServerCriticalValue findByMonGroupIdAndServerResourceIdAndMetricId(Integer monGroupId, Integer serverResourceId, Integer metricId);

    public Collection<MgServerCriticalValue> findAllByMonGroupIdAndServerResourceIdAndMetric_MeasurementId(Integer monitoringGroupId, Integer serverResourceId, Integer measurementId);

}
