package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgApp;
import com.crossent.monitoring.portal.jpa.domain.MgAppPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MgAppRepository extends JpaRepository<MgApp, MgAppPK> {

    public Page<MgApp> findAllByMonGroupId(Pageable pageable, Integer monGroupId);

    public MgApp findByMonGroupIdAndAppResourceId(Integer monGroupId, Integer appResourceId);

    public Page<MgApp> findAllByMonGroupIdAndAppResource_NameLike(Integer monGroupId, Pageable pageable, String applicationName);

    public void deleteByMonGroupIdAndAppResourceIdIn(Integer monGroupId, Integer[] appResourceIds);
    public void deleteByMonGroupIdAndAppResourceId(Integer monGroupId, Integer appResourceId);
}
