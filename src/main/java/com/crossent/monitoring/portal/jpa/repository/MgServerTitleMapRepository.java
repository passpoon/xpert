package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MetaManualMap;
import com.crossent.monitoring.portal.jpa.domain.MetaManualMapPK;
import com.crossent.monitoring.portal.jpa.domain.MgServerTitleMap;
import com.crossent.monitoring.portal.jpa.domain.MgServerTitleMapPK;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface MgServerTitleMapRepository extends CrudRepository<MgServerTitleMap, MgServerTitleMapPK> {

    public Collection<MgServerTitleMap> findAllByMonGroupId(Integer monitoringGroupId);
}
