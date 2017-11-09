package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface MgServerRepository extends JpaRepository<MgServer, MgServerPK> {

    public Page<MgServer> findAllByMonGroupId(Pageable pageable, Integer monGroupId);

    public MgServer findByMonGroupIdAndServerResourceId(Integer monGroupId, Integer serverResourceId);

    public Page<MgServer> findAllByMonGroupIdAndServerResource_NameLike(Integer monGroupId, Pageable pageable, String serverResourceName);
    public Page<MgServer> findAllByMonGroupIdAndServerResource_HostNameLike(Integer monGroupId, Pageable pageable, String hostName);

    public void deleteByMonGroupIdAndServerResourceIdIn(Integer monGroupId, Integer[] serverResourceIds);
    public void deleteByMonGroupIdAndServerResourceId(Integer monGroupId, Integer serverResourceId);

    /*public Page<MgServer> findByNameLike(Pageable pageable, String name);
    public Page<MgServer> findByDescriptionLike(Pageable pageable, String description);*/

    //public MgServer findById(Integer id); 해당 도메인에 찾고자 하는 것이 없을경우 에러
    //public void deleteByIdIn(Integer[] ids);
}
