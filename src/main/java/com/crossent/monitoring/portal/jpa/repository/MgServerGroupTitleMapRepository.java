package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerGroupTitleMap;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupTitleMapPK;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface MgServerGroupTitleMapRepository extends CrudRepository<MgServerGroupTitleMap, MgServerGroupTitleMapPK> {

    Collection<MgServerGroupTitleMap> findAllByMonGroupId(Integer monGroupId);

}
