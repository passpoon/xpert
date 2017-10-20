package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AlarmProcess;
import com.crossent.monitoring.portal.jpa.domain.AlarmProcessPK;
import org.springframework.data.repository.CrudRepository;


public interface AlarmProcessRepository extends CrudRepository<AlarmProcess, AlarmProcessPK> {


}
