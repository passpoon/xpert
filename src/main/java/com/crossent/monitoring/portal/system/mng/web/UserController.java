package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.system.mng.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "시스템 관리 사용자 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value="/system/management/users", method = RequestMethod.GET)
    public PagingResVo pagingUser(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        /*String m1 = MessageUtil.getMessage("monitoring.portal.common.error.test", "하하", "호호");
        String m2 =MessageUtil.getMessage("monitoring.portal.common.error.pagingParameterError");*/

        PagingResVo<User> resPage = userService.pagingUser(paging, search);

        return resPage;
    }

    @ApiOperation(value = "시스템 관리 사용자 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "사용자", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value="/system/management/users", method = RequestMethod.POST)
    public void insertUser(@RequestBody User user){

        userService.insertUser(user);
    }

    @ApiOperation(value = "시스템 관리 사용자 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "사용자 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value="/system/management/users", method = RequestMethod.DELETE)
    public void deleteUsers(@RequestParam String[] userIds){

        userService.deleteUsers(userIds);
    }

    @ApiOperation(value = "시스템 관리 사용자 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 ID", required = true, dataType = "string", paramType = "path"),
    })
    @RequestMapping(value="/system/management/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {

        User outUser =  userService.getUser(userId);
        return outUser;
    }

    @ApiOperation(value = "시스템 관리 사용자 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 ID", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "사용자", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value="/system/management/users/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(@PathVariable String userId, @RequestBody User user) {

        userService.updateUser(userId, user);
    }

    @ApiOperation(value = "시스템 관리 사용자 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 ID", required = true, dataType = "string", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value="/system/management/users/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);
    }
}
