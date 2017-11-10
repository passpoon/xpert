package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.MgUser;
import com.crossent.monitoring.portal.jpa.domain.MgUserPK;

import com.crossent.monitoring.portal.jpa.domain.TypeCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface MgUserRepository extends CrudRepository<MgUser, MgUserPK> {

    public Collection<MgUser> findAllByMonGroupIdAndTypeCodeCode(Integer monGroupId, String typeCode);

    public void deleteByMonGroupIdAndUserIdIn(Integer monGroupId, String[] userIds);
    public void deleteByMonGroupIdAndUserId(Integer monGroupId, String userId);
}
