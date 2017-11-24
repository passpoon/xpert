package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MetaManualMap;
import com.crossent.monitoring.portal.jpa.domain.MetaManualMapPK;
import com.crossent.monitoring.portal.jpa.domain.MgServerTitleMap;
import com.crossent.monitoring.portal.jpa.domain.MgServerTitleMapPK;
import org.springframework.data.repository.CrudRepository;


public interface MgServerTitleMapRepository extends CrudRepository<MgServerTitleMap, MgServerTitleMapPK> {

}
