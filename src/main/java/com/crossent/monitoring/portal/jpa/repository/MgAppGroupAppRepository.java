package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgAppGroupApp;
import com.crossent.monitoring.portal.jpa.domain.MgAppGroupAppPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MgAppGroupAppRepository extends JpaRepository<MgAppGroupApp, MgAppGroupAppPK> {

    public void deleteByMonGroupIdAndAppGroupIdAndAppResourceId(Integer monGroupId, Integer appGroupId, Integer appResourceId);

    public void deleteByMonGroupIdAndAppGroupIdAndAppResourceIdIn(Integer monGroupId, Integer appGroupId, Integer[] appResourceIds);

    public Page<MgAppGroupApp> findAllByMonGroupIdAndAppGroupId(Pageable pageable, Integer monGroupId, Integer groupId);

    public List<MgAppGroupApp> findAllByMonGroupIdAndAppGroupId(Integer monGroupId, Integer groupId);
}
