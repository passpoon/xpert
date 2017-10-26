package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public List<User> listUsers() {

        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<User>();
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User u = iterator.next();

            User user = new User();

            user.setId(u.getId());
            user.setName(u.getName());
            user.setEmail(u.getEmail());
            user.setPhone(u.getPhone());
            user.setChatId(u.getChatId());
            user.setDescription(u.getDescription());

            userList.add(user);
        }
        return userList;
    }

    public PagingResVo<User> pagingUser(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name");

        Page<User> outPage = userRepository.findAll(pagingReqVo.toPagingRequest());

        PagingResVo<User> resPage = new PagingResVo<User>(outPage, true);

        return resPage;
    }

    public void insertUser(User inUser){
        User user = new User();
        user.setId(inUser.getId());
        user.setName(inUser.getName());
        user.setEmail(inUser.getEmail());
        user.setPhone(inUser.getPhone());
        user.setChatId(inUser.getDescription());
        user.setDescription(inUser.getDescription());

        User resUser = userRepository.save(user);

        logger.debug("resUser : {}", resUser);
    }
}
