package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface MgServerGroupRepository extends JpaRepository<MgServerGroup, Integer> {

    public MgServerGroup findByMonGroupId(Integer monitorGroupId);
    public Collection<MgServerGroup> findAllByMonGroupId(Integer monitorGroupId);

    public MgServerGroup findById(Integer serverGroupId);

    public MgServerGroup findByMonGroupIdAndId(Integer monitorGroupId, Integer serverGroupId);

    public Page<MgServerGroup> findAllByMonGroupId(Pageable pageable, Integer monGroupId);
    public Page<MgServerGroup> findAllByMonGroupIdAndMonitoringYn(Pageable pageable, Integer monGroupId, String monitoringYn);
    public Page<MgServerGroup> findAllByMonGroupIdAndMonitoringYnAndDashboardYn(Pageable pageable, Integer monGroupId, String monitoringYn, String dashboardYn);

    public Page<MgServerGroup> findAllByMonGroupIdAndNameLike(Integer monGroupId, Pageable pageable, String name);

    public Page<MgServerGroup> findAllByMonGroupIdAndNameContainsAndMonitoringYn(Pageable pageable, Integer monGroupId, String name, String monitoringYn);
    public Page<MgServerGroup> findAllByMonGroupIdAndNameContainsAndMonitoringYnAndDashboardYn(Pageable pageable, Integer monGroupId, String name, String monitoringYn, String dashboardYn);


    public Page<MgServerGroup> findAllByMonGroupIdAndDescriptionLike(Integer monGroupId, Pageable pageable, String description);

    public void deleteByServerTypeIdIn(Integer[] ids);

    public void deleteByMonGroupIdAndIdIn(Integer monitorGroupId, Integer[] ids);
}
