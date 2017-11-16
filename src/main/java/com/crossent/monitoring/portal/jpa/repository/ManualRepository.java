package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Manual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface ManualRepository extends JpaRepository<Manual, Integer> {

    public Manual findById(Integer id);

    public Page<Manual> findByNameLike(Pageable pageable, String name);
    public Page<Manual> findByLinkLike(Pageable pageable, String link);
    public Page<Manual> findByDescriptionLike(Pageable pageable, String description);

    public void deleteByIdIn(Integer[] ids);

    public Collection<Manual> findAllByNameLikeAndAndMetas_Id(String name, Integer metaId);
}
