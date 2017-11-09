package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgAppCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.MgAppCriticalValuePK;
import org.springframework.data.repository.CrudRepository;


public interface MgAppCriticalValueRepository extends CrudRepository<MgAppCriticalValue, MgAppCriticalValuePK> {

    public MgAppCriticalValue findByMonGroupIdAndAppResourceId(Integer monGroupId, Integer appResourceId);

    public MgAppCriticalValue findByMonGroupIdAndAppResourceIdAndMetricId(Integer monGroupId, Integer appResourceId, Integer metricId);

}
