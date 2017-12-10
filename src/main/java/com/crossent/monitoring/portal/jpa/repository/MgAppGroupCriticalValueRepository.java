package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgAppGroupCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.MgAppGroupCriticalValuePK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MgAppGroupCriticalValueRepository extends CrudRepository<MgAppGroupCriticalValue, MgAppGroupCriticalValuePK> {

    public MgAppGroupCriticalValue findByMgAppGroupIdAndMetricId(Integer appGroupId, Integer metricId);
    public List<MgAppGroupCriticalValue> findAllByMgAppGroupId(Integer appGroupId);
}
