package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import com.crossent.monitoring.portal.system.mng.service.UserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class UserGroupController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserGroupController.class);
    @Autowired
    UserGroupService userGroupService;

    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.GET)
    public PagingResVo pagingUserGroup(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<UserGroup> resPage = userGroupService.pagingUserGroup(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.POST)
    public void insertUserGroup(@RequestBody UserGroup userGroup){

        userGroupService.insertUserGroup(userGroup);
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.DELETE)
    public void deleteUserGroups(@RequestParam String[] userGroupIds){

        userGroupService.deleteUserGroups(userGroupIds);
    }

    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.GET)
    public UserGroup getUserGroup(@PathVariable String userGroupId){

        UserGroup getUser =  userGroupService.getUserGroup(userGroupId);

        return getUser;
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.PUT)
    public void updateUserGroup(@PathVariable String userGroupId, @RequestBody UserGroup userGroup){

        userGroupService.updateUserGroup(userGroupId, userGroup);
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.DELETE)
    public void deleteUserGroup(@PathVariable String userGroupId){

        userGroupService.deleteUserGroup(userGroupId);
    }

    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users" , method = RequestMethod.GET)
    public Collection<User> getUserGroupUsers(@PathVariable String userGroupId){

        Collection<User> users = userGroupService.getUserGroupUsers(userGroupId);
        return users;
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users" , method = RequestMethod.POST)
    public void insertUserGroupUsers(@PathVariable String userGroupId, @RequestBody String[] userIds){
        /*logger.debug("userGroupId : {}", userGroupId);
        if(userIds == null){
            logger.debug("userIds is null");
        }else {
            logger.debug("userIds : {}", Arrays.toString(userIds));
        }*/
        userGroupService.insertUserGroupUsers(userGroupId, userIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users" , method = RequestMethod.DELETE)
    public void deleteUserGroupUsers(@PathVariable String userGroupId, @RequestParam String[] userIds) {

        userGroupService.deleteUserGroupUsers(userGroupId, userIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users/{userId}" , method = RequestMethod.DELETE)
    public void deleteUserGroupUser(@PathVariable String userGroupId, @PathVariable String userId) {

        userGroupService.deleteUserGroupUser(userGroupId, userId);
    }

}
