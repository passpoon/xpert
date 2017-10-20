package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.UserGroupMap;
import com.crossent.monitoring.portal.jpa.domain.UserGroupMapPK;
import org.springframework.data.repository.CrudRepository;


public interface UserGroupMapRepository extends CrudRepository<UserGroupMap, UserGroupMapPK> {


}
