package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgAlarmUser;
import com.crossent.monitoring.portal.jpa.domain.MgAlarmUserPK;
import org.springframework.data.repository.CrudRepository;


public interface MgAlarmUserRepository extends CrudRepository<MgAlarmUser, MgAlarmUserPK> {


}
