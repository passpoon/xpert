package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.TypeCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface TypeCodeRepository extends CrudRepository<TypeCode, String> {

    public Collection<TypeCode> findAllByCodeLike(String code);
}
