package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import org.springframework.data.repository.CrudRepository;


public interface UserGroupRepository extends CrudRepository<UserGroup, String> {


}
