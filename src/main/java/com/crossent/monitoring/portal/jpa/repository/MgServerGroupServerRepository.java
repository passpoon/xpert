package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgServerGroupServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupServerPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface MgServerGroupServerRepository extends CrudRepository<MgServerGroupServer, MgServerGroupServerPK> {

    public void deleteByMonGroupIdAndServerGroupIdAndServerResourceIdIn(Integer monGroupId, Integer serverGroupId, Integer[] serverResourceIds);

    public void deleteByMonGroupIdAndServerGroupIdAndServerResourceId(Integer monGroupId, Integer serverGroupId, Integer serverResourceId);

    public Page<MgServerGroupServer> findAllByMonGroupIdAndServerGroupId(Pageable pageable, Integer monGroupId, Integer serverGroupId);


}
