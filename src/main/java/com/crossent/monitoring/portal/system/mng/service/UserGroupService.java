package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import com.crossent.monitoring.portal.jpa.domain.UserGroupMap;
import com.crossent.monitoring.portal.jpa.repository.UserGroupMapRepository;
import com.crossent.monitoring.portal.jpa.repository.UserGroupRepository;
import com.crossent.monitoring.portal.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserGroupService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGroupMapRepository userGroupMapRepository;

//    @Autowired
//    UserGroupDtoRepository userGroupDtoRepository;

    public PagingResVo<UserGroup> pagingUserGroup(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if(keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<UserGroup> userGroups = null;
        if(key == null){
            //TODO 전체조회
            userGroups = userGroupRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "id":
                {
                    userGroups = userGroupRepository.findByIdLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "name":
                {
                    userGroups = userGroupRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description":
                {
                    userGroups = userGroupRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        PagingResVo<UserGroup> resPage = new PagingResVo<UserGroup>(userGroups, true);

        return resPage;
    }


    public void insertUserGroup(UserGroup userGroup) {

        UserGroup inUserGroup = new UserGroup();
        inUserGroup.setId(userGroup.getId());
        inUserGroup.setName(userGroup.getName());
        inUserGroup.setDescription(userGroup.getDescription());

        UserGroup resUser = userGroupRepository.save(inUserGroup);
    }

    public void deleteUserGroups(String[] delUserGroups) {

        userGroupRepository.deleteByIdIn(delUserGroups);
    }

    public UserGroup getUserGroup(String userGroupId) {

        UserGroup user = userGroupRepository.findOne(userGroupId);

        UserGroup out = new UserGroup();
        out.setId(user.getId());
        out.setName(user.getName());
        out.setDescription(user.getDescription());

        return out;
    }

    public UserGroup updateUserGroup(String userGroupId, UserGroup userGroup){

        UserGroup getData = userGroupRepository.findOne(userGroupId);

        if(getData == null) {
            return null;
        }
        getData.setName(userGroup.getName());
        getData.setDescription(userGroup.getDescription());

        UserGroup updateData = userGroupRepository.save(getData);

        return updateData;
    }

    public void deleteUserGroup(String userGroupId) {

        userGroupRepository.delete(userGroupId);
    }

    public Collection<User> getUserGroupUsers(String userGroupId){

        UserGroup userGroup = userGroupRepository.findById(userGroupId);
        Collection<User> users = userGroup.getUsers();

        return users;
    }

    public void insertUserGroupUser(String userGroupId, User reqUser){

//        UserGroup userGroup = userGroupRepository.findById(userGroupId);
//
//        User user = userRepository.findOne(reqUser.getId());

//        UserGroupMap userGroupMap = new UserGroupMap();
//        userGroupMap.setUser(user);
//        userGroupMap.setUserGroup(userGroup);
        //(두 관계 매핑할 때) map의 경우 PK만 불러와서 mapping
        UserGroupMap userGroupMap = new UserGroupMap();
        userGroupMap.setUserGroupId(userGroupId);  // userGroupId
        userGroupMap.setUserId(reqUser.getId());  // User userId

        UserGroupMap groupMap = userGroupMapRepository.save(userGroupMap);
    }

    public void deleteUserGroupUsers(String userGroupId, String[] delUsers) {

        userGroupMapRepository.deleteByUserGroupIdAndUserIdIn(userGroupId, delUsers);
    }

    public void deleteUserGroupUser(String userGroupId, String userId) {

        userGroupMapRepository.deleteByUserGroupIdAndUserId(userGroupId, userId);
    }



}
