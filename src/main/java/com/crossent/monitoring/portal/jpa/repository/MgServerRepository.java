package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface MgServerRepository extends JpaRepository<MgServer, MgServerPK> {

    public Page<MgServer> findAllByMonGroupIdAndMonitoringYn(Pageable pageable, Integer monGroupId, String monitoringYn);

    public Page<MgServer> findAllByMonGroupId(Pageable pageable, Integer monGroupId);

    public MgServer findByMonGroupIdAndServerResourceId(Integer monGroupId, Integer serverResourceId);

    public Page<MgServer> findAllByMonGroupIdAndMonitoringYnAndServerResource_NameLike(Pageable pageable, Integer monGroupId, String monitoringYn,String serverResourceName);
    public Page<MgServer> findAllByMonGroupIdAndServerResource_NameLike(Pageable pageable, Integer monGroupId, String serverResourceName);
    public Page<MgServer> findAllByMonGroupIdAndMonitoringYnAndServerResource_HostNameLike(Pageable pageable, Integer monGroupId, String monitoringYn, String hostName);
    public Page<MgServer> findAllByMonGroupIdAndServerResource_HostNameLike(Pageable pageable, Integer monGroupId, String hostName);

    public void deleteByMonGroupIdAndServerResourceIdIn(Integer monGroupId, Integer[] serverResourceIds);
    public void deleteByMonGroupIdAndServerResourceId(Integer monGroupId, Integer serverResourceId);

    /*public Page<MgServer> findByNameLike(Pageable pageable, String name);
    public Page<MgServer> findByDescriptionLike(Pageable pageable, String description);*/

    //public MgServer findById(Integer id); 해당 도메인에 찾고자 하는 것이 없을경우 에러
    //public void deleteByIdIn(Integer[] ids);

    public List<MgServer> findAllByMonGroupId(Integer monGroupId);
}
