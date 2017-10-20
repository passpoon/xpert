package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {

    public Measurement findByName(@Param("name") String Name);

}
