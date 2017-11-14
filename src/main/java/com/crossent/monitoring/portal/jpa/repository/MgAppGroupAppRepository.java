package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgAppGroupApp;
import com.crossent.monitoring.portal.jpa.domain.MgAppGroupAppPK;
import org.springframework.data.repository.CrudRepository;


public interface MgAppGroupAppRepository extends CrudRepository<MgAppGroupApp, MgAppGroupAppPK> {

    public void deleteByMonGroupIdAndAppGroupIdAndAppResourceId(Integer monGroupId, Integer appGroupId, Integer appResourceId);
}
