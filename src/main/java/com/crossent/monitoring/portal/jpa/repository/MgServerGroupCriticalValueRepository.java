package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerGroupCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupCriticalValuePK;
import org.springframework.data.repository.CrudRepository;


public interface MgServerGroupCriticalValueRepository extends CrudRepository<MgServerGroupCriticalValue, MgServerGroupCriticalValuePK> {

    public MgServerGroupCriticalValue findByMonGroupIdAndServerGroupId(Integer monGroupId, Integer serverGroupId);

    public MgServerGroupCriticalValue findByMonGroupIdAndServerGroupIdAndMetricId(Integer monGroupId, Integer serverGroupId, Integer metricId);
}
