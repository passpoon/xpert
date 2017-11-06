package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AppInfoRepository extends JpaRepository<AppInfo, Integer> {

    public AppInfo findById(Integer id);

    public Page<AppInfo> findByNameLike(Pageable pageable, String name);
    public Page<AppInfo> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);
}
