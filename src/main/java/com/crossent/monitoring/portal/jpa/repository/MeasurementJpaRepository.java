package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeasurementJpaRepository extends JpaRepository<Measurement, Integer> {


}
