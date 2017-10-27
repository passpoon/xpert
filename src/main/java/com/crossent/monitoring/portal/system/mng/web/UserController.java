package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.sample.web.MeasurementController;
import com.crossent.monitoring.portal.system.mng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
public class UserController extends BaseController {

    Logger logger = LoggerFactory.getLogger(MeasurementController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value="/system/management/users", method = RequestMethod.GET)
    public PagingResVo pagingUser(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search")SearchReqVo search){

        /*String m1 = MessageUtil.getMessage("monitoring.portal.common.error.test", "하하", "호호");
        String m2 =MessageUtil.getMessage("monitoring.portal.common.error.pagingParameterError");*/

        PagingResVo<User> resPage = userService.pagingUser(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value="/system/management/users", method = RequestMethod.POST)
    public void insertUser(@RequestBody User user){

        userService.insertUser(user);
    }

    @Transactional
    @RequestMapping(value="/system/management/users", method = RequestMethod.DELETE)
    public void deleteUsers(@RequestParam String[] userIds){

        userService.deleteUsers(userIds);
    }

    @RequestMapping(value="/system/management/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {

        User outUser =  userService.getUser(userId);
        return outUser;
    }

    @Transactional
    @RequestMapping(value="/system/management/users/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(@PathVariable String userId, @RequestBody User user) {

        User aaaa = userService.getUser(userId);
        if (aaaa == null ){
            logger.error("Unable to update. User with id {} not found.", userId);
        }

        aaaa.setName(user.getName());
        aaaa.setDescription(user.getDescription());

        userService.updateUser(userId, user);
    }

    @Transactional
    @RequestMapping(value="/system/management/users/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);

    }





}
