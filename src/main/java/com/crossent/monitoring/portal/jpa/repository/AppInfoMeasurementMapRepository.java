package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AppInfoMeasurementMap;
import com.crossent.monitoring.portal.jpa.domain.AppInfoMeasurementMapPK;
import org.springframework.data.repository.CrudRepository;


public interface AppInfoMeasurementMapRepository extends CrudRepository<AppInfoMeasurementMap, AppInfoMeasurementMapPK> {

    public void deleteByAppInfoIdAndMeasurementIdIn(Integer id, Integer[] ids);
    public void deleteByAppInfoIdAndMeasurementId(Integer appInfoId, Integer measurementId);
}
