package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import org.springframework.data.repository.CrudRepository;


public interface ServerResourceRepository extends CrudRepository<ServerResource, Integer> {


}
