package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.Meta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface MetaRepository extends JpaRepository<Meta, Integer> {

    public Meta findById(Integer id);

    public Page<Meta> findByProgLike(Pageable pageable, String prog);
    public Page<Meta> findByLogSourceLike(Pageable pageable, String logSource);
    public Page<Meta> findByPatternLike(Pageable pageable, String pattern);
    public Page<Meta> findByStateCodeLike(Pageable pageable, String stateCode);

    public void deleteByIdIn(Integer[] ids);
}
