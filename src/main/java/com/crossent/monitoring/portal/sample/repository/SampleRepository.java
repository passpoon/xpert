package com.crossent.monitoring.portal.sample.repository;

import com.crossent.monitoring.portal.jpa.domain.Test;
import org.springframework.data.repository.CrudRepository;


public interface SampleRepository extends CrudRepository<Test, Integer> {


}
