package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.system.mng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/system/management/users", method = RequestMethod.GET)
    public List<User> listUser() {

        List<User> userList = userService.listUsers();

        return userList;
    }

    @RequestMapping(value="/system/management/users/paging", method = RequestMethod.GET)
    public PagingResVo pagingUser(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search")SearchReqVo search){

        String m1 = MessageUtil.getMessage("monitoring.portal.common.error.test", "하하", "호호");
        String m2 =MessageUtil.getMessage("monitoring.portal.common.error.pagingParameterError");

        PagingResVo<User> resPage = userService.pagingUser(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value="/system/management/users", method = RequestMethod.POST)
    public void insertUser(@RequestBody User user){

        userService.insertUser(user);
    }


}
