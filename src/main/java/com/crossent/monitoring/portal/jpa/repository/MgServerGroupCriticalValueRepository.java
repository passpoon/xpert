package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerGroupCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupCriticalValuePK;
import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.nodes.CollectionNode;

import java.util.Collection;


public interface MgServerGroupCriticalValueRepository extends CrudRepository<MgServerGroupCriticalValue, MgServerGroupCriticalValuePK> {

    /*public MgServerGroupCriticalValue findByMonGroupIdAndServerGroupId(Integer monGroupId, Integer serverGroupId);*/

    public MgServerGroupCriticalValue findByServerGroupIdAndMetricId(Integer serverGroupId, Integer metricId);

    public Collection<MgServerGroupCriticalValue> findAllByServerGroupIdAndMetric_MeasurementId(Integer serverGroupId, Integer measurementId );

    public void deleteByServerGroupIdAndMetricIdIn(Integer serverGroupId, Integer[] metricIds);
}
