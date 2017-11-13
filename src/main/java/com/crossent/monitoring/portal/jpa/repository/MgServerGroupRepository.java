package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MgServerGroupRepository extends JpaRepository<MgServerGroup, Integer> {

    public MgServerGroup findByMonGroupId(Integer monitorGroupId);

    public MgServerGroup findByMonGroupIdAndId(Integer monitorGroupId, Integer serverGroupId);

    public Page<MgServerGroup> findAllByMonGroupId(Pageable pageable, Integer monGroupId);

    public Page<MgServerGroup> findAllByMonGroupIdAndNameLike(Integer monGroupId, Pageable pageable, String name);
    public Page<MgServerGroup> findAllByMonGroupIdAndDescriptionLike(Integer monGroupId, Pageable pageable, String description);

    public void deleteByServerTypeIdIn(Integer[] ids);
}
