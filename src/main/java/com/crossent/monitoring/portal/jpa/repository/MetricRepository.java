package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Metric;
import org.springframework.data.repository.CrudRepository;


public interface MetricRepository extends CrudRepository<Metric, Integer> {


}
