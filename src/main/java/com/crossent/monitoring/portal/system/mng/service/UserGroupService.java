package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import com.crossent.monitoring.portal.jpa.repository.UserGroupRepository;
import com.crossent.monitoring.portal.system.mng.dto.UserGroupDto;
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

    public PagingResVo<UserGroupDto> pagingUserGroup(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

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
        Page<UserGroupDto> userGroups = null;
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

        PagingResVo<UserGroupDto> resPage = new PagingResVo<UserGroupDto>(userGroups, true);

        return resPage;
    }


    public void insertUserGroup(UserGroupDto userGroup) {

        UserGroupDto inUserGroup = new UserGroupDto();
        inUserGroup.setId(userGroup.getId());
        inUserGroup.setName(userGroup.getName());
        inUserGroup.setDescription(userGroup.getDescription());

        UserGroupDto resUser = userGroupRepository.save(inUserGroup);

    }

    public void deleteUserGroups(String[] delUserGroups) {

        userGroupRepository.deleteByIdIn(delUserGroups);
    }

    public UserGroupDto getUserGroup(String userGroupId) {

        UserGroupDto user = userGroupRepository.findOne(userGroupId);

        UserGroupDto out = new UserGroupDto();
        out.setId(user.getId());
        out.setName(user.getName());
        out.setDescription(user.getDescription());

        return out;
    }

    public UserGroupDto updateUserGroup(String userGroupId, UserGroupDto userGroup){

        UserGroupDto getData = userGroupRepository.findOne(userGroupId);

        if(getData == null) {
            return null;
        }
        getData.setName(userGroup.getName());
        getData.setDescription(userGroup.getDescription());

        UserGroupDto updateData = userGroupRepository.save(getData);

        return updateData;
    }

    public void deleteUserGroup(String userGroupId) {

        userGroupRepository.delete(userGroupId);
    }

    /*public Collection<User> getUserGroupUsers(String userGroupId){

        UserGroupDto one = userGroupRepository.findOne(userGroupId);
        logger.debug("one ::::", one);
        Collection<User> collection = one.getUsers();
        return collection;
    }*/





}
