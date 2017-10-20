package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgUser;
import com.crossent.monitoring.portal.jpa.domain.MgUserPK;
import org.springframework.data.repository.CrudRepository;


public interface MgUserRepository extends CrudRepository<MgUser, MgUserPK> {


}
