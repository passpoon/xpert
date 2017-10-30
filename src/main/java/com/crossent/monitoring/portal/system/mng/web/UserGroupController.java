package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import com.crossent.monitoring.portal.system.mng.dto.UserGroupDto;
import com.crossent.monitoring.portal.system.mng.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@RestController
public class UserGroupController extends BaseController {

    @Autowired
    UserGroupService userGroupService;

    @RequestMapping(value="/system/management/user-groups", method = RequestMethod.GET)
    public PagingResVo pagingUserGroup(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<UserGroupDto> resPage = userGroupService.pagingUserGroup(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.POST)
    public void insertUserGroup(@RequestBody UserGroupDto userGroup){

        userGroupService.insertUserGroup(userGroup);
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.DELETE)
    public void deleteUserGroups(@RequestParam String[] userGroupIds){

        userGroupService.deleteUserGroups(userGroupIds);
    }

    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.GET)
    public UserGroupDto getUserGroup(@PathVariable String userGroupId){

        UserGroupDto getUser =  userGroupService.getUserGroup(userGroupId);

        return getUser;
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.PUT)
    public void updateUserGroup(@PathVariable String userGroupId, @RequestBody UserGroupDto userGroup){

        userGroupService.updateUserGroup(userGroupId, userGroup);
    }

    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.DELETE)
    public void deleteUserGroup(@PathVariable String userGroupId){

        userGroupService.deleteUserGroup(userGroupId);
    }

/*    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users" , method = RequestMethod.GET)
    public Collection<User> getUserGroupUsers(@PathVariable String userGroupId){

        Collection<User> userGroupUsers = userGroupService.getUserGroupUsers(userGroupId);
        return userGroupUsers;
    }*/

}
