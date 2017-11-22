package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AppInfoCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.AppInfoCriticalValuePK;
import org.springframework.data.repository.CrudRepository;


public interface AppInfoCriticalValueRepository extends CrudRepository<AppInfoCriticalValue, AppInfoCriticalValuePK> {

    public AppInfoCriticalValue findAllByAppInfoIdAndMeasurementIdAndMetricId(Integer appInfoId, Integer measurementId, Integer metricId);

}

