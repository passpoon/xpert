package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerPK;
import org.springframework.data.repository.CrudRepository;


public interface MgServerRepository extends CrudRepository<MgServer, MgServerPK> {


}
