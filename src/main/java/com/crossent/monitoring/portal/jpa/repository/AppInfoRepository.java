package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface AppInfoRepository extends JpaRepository<AppInfo, Integer> {

    public AppInfo findById(Integer id);

    public Page<AppInfo> findByNameLike(Pageable pageable, String name);
    public Page<AppInfo> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);

    public Collection<AppInfo> findAllByNameLikeAndAppResources_Id(String name, Integer appResourceId);

    public Page<AppInfo> findAllById(Pageable pageable, Integer id);
    public Page<AppInfo> findAllByIdAndMeasurements_NameLike(Pageable pageable, Integer id, String name);
}
