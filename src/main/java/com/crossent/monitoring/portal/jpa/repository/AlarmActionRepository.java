package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AlarmAction;
import com.crossent.monitoring.portal.jpa.domain.AlarmActionPK;
import org.springframework.data.repository.CrudRepository;


public interface AlarmActionRepository extends CrudRepository<AlarmAction, AlarmActionPK> {


}
