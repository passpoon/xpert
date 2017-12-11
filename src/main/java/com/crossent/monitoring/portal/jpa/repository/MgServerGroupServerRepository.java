package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerGroupServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupServerPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface MgServerGroupServerRepository extends JpaRepository<MgServerGroupServer, MgServerGroupServerPK> {

    public void deleteByMonGroupIdAndServerGroupIdAndServerResourceIdIn(Integer monGroupId, Integer serverGroupId, Integer[] serverResourceIds);

    public void deleteByMonGroupIdAndServerGroupIdAndServerResourceId(Integer monGroupId, Integer serverGroupId, Integer serverResourceId);

    public Page<MgServerGroupServer> findAllByMonGroupIdAndServerGroupId(Pageable pageable, Integer monGroupId, Integer serverGroupId);

    public Collection<MgServerGroupServer> findAllByMonGroupIdAndServerGroupId(Integer monGroupId, Integer serverGroupId);


}
