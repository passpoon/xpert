package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import com.crossent.monitoring.portal.system.mng.service.UserGroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "시스템 관리 사용자 그룹 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.GET)
    public PagingResVo pagingUserGroup(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        if(logger.isDebugEnabled()) {
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }
        PagingResVo<UserGroup> resPage = userGroupService.pagingUserGroup(paging, search);
        if(logger.isDebugEnabled()) {
            logger.debug("resPage : {}", resPage);
        }
        return resPage;
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroup", value = "사용자 그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.POST)
    public void insertUserGroup(@RequestBody UserGroup userGroup){

        if(logger.isDebugEnabled()) {
            logger.debug("userGroup : {}", userGroup);
        }
        userGroupService.insertUserGroup(userGroup);
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupIds", value = "사용자 그룹 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/user-groups", method = RequestMethod.DELETE)
    public void deleteUserGroups(@RequestParam String[] userGroupIds){

        if(logger.isDebugEnabled()) {
            logger.debug("userGroupIds : {}", Arrays.toString(userGroupIds));
        }
        userGroupService.deleteUserGroups(userGroupIds);
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupId", value = "사용자 그룹 ID", required = true, dataType = "string", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.GET)
    public UserGroup getUserGroup(@PathVariable String userGroupId){

        if(logger.isDebugEnabled()) {
            logger.debug("userGroupId : {}", userGroupId);
        }
        UserGroup getUser =  userGroupService.getUserGroup(userGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("getUser : {}", getUser);
        }
        return getUser;
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupId", value = "사용자 그룹 ID", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userGroup", value = "사용자 그룹", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.PUT)
    public void updateUserGroup(@PathVariable String userGroupId, @RequestBody UserGroup userGroup){

        if(logger.isDebugEnabled()) {
            logger.debug("userGroupId : {}", userGroupId);
            logger.debug("userGroup : {}", userGroup);
        }
        userGroupService.updateUserGroup(userGroupId, userGroup);
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupId", value = "사용자 그룹 ID", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}", method = RequestMethod.DELETE)
    public void deleteUserGroup(@PathVariable String userGroupId){

        if(logger.isDebugEnabled()) {
            logger.debug("userGroupId : {}", userGroupId);
        }
        userGroupService.deleteUserGroup(userGroupId);
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 사용자 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupId", value = "사용자 그룹 ID", required = true, dataType = "string", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users" , method = RequestMethod.GET)
    public Collection<User> getUserGroupUsers(@PathVariable String userGroupId){

        if(logger.isDebugEnabled()) {
            logger.debug("userGroupId : {}", userGroupId);
        }
        Collection<User> users = userGroupService.getUserGroupUsers(userGroupId);
        if(logger.isDebugEnabled()) {
            logger.debug("users : {}", users);
        }
        return users;
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 사용자 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupId", value = "사용자 그룹 ID", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userIds", value = "사용자 ID 목록", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users" , method = RequestMethod.POST)
    public void insertUserGroupUsers(@PathVariable String userGroupId, @RequestBody String[] userIds){
        if(logger.isDebugEnabled()) {
            logger.debug("userGroupId : {}", userGroupId);
            logger.debug("userIds : {}", Arrays.toString(userIds));
        }
        userGroupService.insertUserGroupUsers(userGroupId, userIds);
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 사용자 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupId", value = "사용자 그룹 ID", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userIds", value = "사용자 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users" , method = RequestMethod.DELETE)
    public void deleteUserGroupUsers(@PathVariable String userGroupId, @RequestParam String[] userIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("userGroupId : {}", userGroupId);
            logger.debug("userIds : {}", Arrays.toString(userIds));
        }
        userGroupService.deleteUserGroupUsers(userGroupId, userIds);
    }

    @ApiOperation(value = "시스템 관리 사용자 그룹 사용자 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userGroupId", value = "사용자 그룹 ID", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "사용자 ID", required = true, dataType = "string", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/user-groups/{userGroupId}/users/{userId}" , method = RequestMethod.DELETE)
    public void deleteUserGroupUser(@PathVariable String userGroupId, @PathVariable String userId) {

        if(logger.isDebugEnabled()) {
            logger.debug("userGroupId : {}", userGroupId);
            logger.debug("userId : {}", userId);
        }
        userGroupService.deleteUserGroupUser(userGroupId, userId);
    }

}
