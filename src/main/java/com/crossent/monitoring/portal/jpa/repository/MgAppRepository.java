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

    public Page<MgApp> findAllByMonGroupIdAndMonitoringYn(Pageable pageable, Integer monGroupId, String monitoringYn);
    public Page<MgApp> findAllByMonGroupIdAndMonitoringYnAndDashboardYn(Pageable pageable, Integer monGroupId, String monitoringYn, String dashboardYn);
    public Page<MgApp> findAllByMonGroupIdAndMonitoringYnAndAppResource_ServerResource_HostNameLike(Pageable pageable, Integer monGroupId, String monitoringYn, String hostName);
    public Page<MgApp> findAllByMonGroupIdAndMonitoringYnAndDashboardYnAndAppResource_ServerResource_HostNameLike(Pageable pageable, Integer monGroupId, String monitoringYn, String dashboardYn, String hostName);
    public Page<MgApp> findAllByMonGroupIdAndMonitoringYnAndAppResource_NameLike(Pageable pageable, Integer monGroupId, String monitoringYn, String appName);
    public Page<MgApp> findAllByMonGroupIdAndMonitoringYnAndDashboardYnAndAppResource_NameLike(Pageable pageable, Integer monGroupId, String monitoringYn, String dashBoardYn, String appName);
    //public Page<MgApp> find
}
