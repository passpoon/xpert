package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.UserGroupMap;
import com.crossent.monitoring.portal.jpa.domain.UserGroupMapPK;
import org.springframework.data.repository.CrudRepository;


public interface UserGroupMapRepository extends CrudRepository<UserGroupMap, UserGroupMapPK> {

    public void deleteByUserId(String userId);
    public void deleteByUserId(String[] userId);
    public void deleteByUserGroupIdAndUserIdIn(String userGroupId, String[] ids);
    public void deleteByUserGroupIdAndUserId(String userGroupId, String userId);
}
