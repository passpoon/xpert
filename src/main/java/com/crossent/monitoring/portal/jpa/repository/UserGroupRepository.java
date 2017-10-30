package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface UserGroupRepository extends JpaRepository<UserGroup, String> {

    public UserGroup findById(String id);

    public Page<UserGroup> findByNameLike(Pageable pageable, String name);
    public Page<UserGroup> findByIdLike(Pageable pageable, String id);
    public Page<UserGroup> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(String[] ids);
}
