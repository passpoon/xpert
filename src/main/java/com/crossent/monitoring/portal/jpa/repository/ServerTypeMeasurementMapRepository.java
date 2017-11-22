package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.ServerTypeMeasurementMap;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeMeasurementMapPK;
import org.springframework.data.repository.CrudRepository;


public interface ServerTypeMeasurementMapRepository extends CrudRepository<ServerTypeMeasurementMap, ServerTypeMeasurementMapPK> {

    public void deleteByServerTypeIdAndMeasurementIdIn(Integer serverTypeId, Integer[] measurementIds);
    public void deleteByServerTypeIdAndMeasurementId(Integer serverTypeId, Integer measurementId);

    public void deleteByServerTypeIdIn(Integer[] serverTypeIds);
}
