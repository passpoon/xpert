package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface MonGroupRepository extends JpaRepository<MonGroup, Integer> {

    public MonGroup findById(Integer id);

    public Page<MonGroup> findByNameLike(Pageable pageable, String name);
    public Page<MonGroup> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);
}
