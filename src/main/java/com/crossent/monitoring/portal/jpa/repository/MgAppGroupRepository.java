package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgAppGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MgAppGroupRepository extends JpaRepository<MgAppGroup, Integer> {

    public Page<MgAppGroup> findAllByMonGroupId(Pageable pageable, Integer monGroupId);

    public Page<MgAppGroup> findAllByMonGroupIdAndNameLike(Integer monGroupId, Pageable pageable, String name);
    public Page<MgAppGroup> findAllByMonGroupIdAndDescriptionLike(Integer monGroupId, Pageable pageable, String description);

    public MgAppGroup findByMonGroupIdAndId(Integer monGroupId, Integer appGroupId);

    public MgAppGroup findById(Integer appGroupId);

    public void deleteByMonGroupIdAndIdIn(Integer monGroupId, Integer[] Ids);
    public Page<MgAppGroup> findAllByMonGroupIdAndMonitoringYn(Pageable pageable, Integer monGroupId, String monitoringYn);
    public Page<MgAppGroup> findAllByMonGroupIdAndNameContainsAndMonitoringYn(Pageable pageable, Integer monGroupId, String name, String monitoringYn);
}
