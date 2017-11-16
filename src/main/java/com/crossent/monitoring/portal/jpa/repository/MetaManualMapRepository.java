package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MetaManualMap;
import com.crossent.monitoring.portal.jpa.domain.MetaManualMapPK;
import org.springframework.data.repository.CrudRepository;


public interface MetaManualMapRepository extends CrudRepository<MetaManualMap, MetaManualMapPK> {

    public void deleteByMetaIdAndManualIdIn(Integer metaId, Integer[] manualIds);
    public void deleteByMetaIdAndManualId(Integer metaId, Integer manualId);
}
