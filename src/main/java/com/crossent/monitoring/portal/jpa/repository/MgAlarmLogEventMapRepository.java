package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgAlarmLogEventMap;
import com.crossent.monitoring.portal.jpa.domain.MgAlarmLogEventMapPK;
import org.springframework.data.repository.CrudRepository;


public interface MgAlarmLogEventMapRepository extends CrudRepository<MgAlarmLogEventMap, MgAlarmLogEventMapPK> {


}
