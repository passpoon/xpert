package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import org.springframework.data.repository.CrudRepository;


public interface MonGroupRepository extends CrudRepository<MonGroup, Integer> {


}
