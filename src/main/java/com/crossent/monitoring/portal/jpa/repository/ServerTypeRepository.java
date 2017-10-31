package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.ServerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface ServerTypeRepository extends JpaRepository<ServerType, Integer> {

    public ServerType findById(Integer id);

//    public Page<ServerType> findByIdLike(Pageable pageable, Integer id);
    public Page<ServerType> findByNameLike(Pageable pageable, String name);
    public Page<ServerType> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);
}
