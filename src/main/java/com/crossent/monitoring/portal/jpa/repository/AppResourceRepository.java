package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AppResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;


public interface AppResourceRepository extends JpaRepository<AppResource, Integer> {

    public AppResource findById(Integer id);
    public List<AppResource> findAllByIdIn(List<Integer> ids);

    public Page<AppResource> findByNameLike(Pageable pageable, String name);
    public Page<AppResource> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);

    public Collection<AppResource> findAllByNameLikeAndMonGroups_Id(String name, Integer monitoringGroupId);
}
