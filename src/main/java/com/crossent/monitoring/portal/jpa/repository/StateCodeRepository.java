package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.StateCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface StateCodeRepository extends CrudRepository<StateCode, Integer> {

    public Collection<StateCode> findAllByCodeLike(String code);
}
