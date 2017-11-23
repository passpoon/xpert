package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AppInfoCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.AppInfoCriticalValuePK;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface AppInfoCriticalValueRepository extends CrudRepository<AppInfoCriticalValue, AppInfoCriticalValuePK> {

    public AppInfoCriticalValue findByAppInfoIdAndMeasurementIdAndMetricId(Integer appInfoId, Integer measurementId, Integer metricId);
    public Collection<AppInfoCriticalValue> findAllByAppInfoIdAndMeasurementId(Integer appInfoId, Integer measurementId);

    public void deleteByAppInfoIdAndMeasurementIdAndMetricIdIn(Integer appInfoId, Integer measurementId, Integer[] metricIds);
    public void deleteByAppInfoIdAndMeasurementIdAndMetricId(Integer appInfoId, Integer measurementId, Integer metricId);
}

