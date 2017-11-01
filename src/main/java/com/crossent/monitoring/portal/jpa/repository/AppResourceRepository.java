package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AppResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AppResourceRepository extends JpaRepository<AppResource, Integer> {

    public AppResource findById(Integer id);

    public Page<AppResource> findByNameLike(Pageable pageable, String name);
    public Page<AppResource> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);
}
