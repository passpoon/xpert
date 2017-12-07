package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;


public interface ServerResourceRepository extends JpaRepository<ServerResource, Integer> {

    public ServerResource findById(Integer id);
    public Collection<ServerResource> findAllByIdIn(List<Integer> ids);

    public Page<ServerResource> findByNameLike(Pageable pageable, String name);
    public Page<ServerResource> findByHostNameLike(Pageable pageable, String hostName);
    public Page<ServerResource> findByIpLike(Pageable pageable, String ip);
    public Page<ServerResource> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);
    public void deleteByServerTypeIdIn(Integer[] serverTypeIds);

    public Collection<ServerResource> findAllByNameLikeAndMonGroups_Id(String name, Integer monitoringGroupId);
}
