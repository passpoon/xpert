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
import java.util.Map;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public PagingResVo<User> pagingUser(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

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
        Page<User> users = null;
        if(key == null){
            //TODO 전체조회
            users = userRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "id":
                {
                    users = userRepository.findByIdLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "name":
                {
                    users = userRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "email":
                {
                    users = userRepository.findByEmailLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        //Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name");
        //Page<User> outPage = userRepository.findAll(pagingReqVo.toPagingRequest());

        PagingResVo<User> resPage = new PagingResVo<User>(users, true);

        return resPage;
    }

    public void insertUser(User inUser) {

        User user = new User();
        user.setId(inUser.getId());
        user.setName(inUser.getName());
        user.setEmail(inUser.getEmail());
        user.setPhone(inUser.getPhone());
        user.setChatId(inUser.getDescription());
        user.setDescription(inUser.getDescription());
        user.setUuid(inUser.getUuid());

        User resUser = userRepository.save(user);

        logger.debug("resUser : {}", resUser);
    }

    public void deleteUsers(String[] delUsers) {

        userRepository.deleteByIdIn(delUsers);
    }

    public User getUser(String userId) {

        User user = userRepository.findOne(userId);

        User out = new User();
        out.setId(user.getId());
        out.setName(user.getName());
        out.setEmail(user.getEmail());
        out.setPhone(user.getPhone());
        out.setChatId(user.getChatId());
        out.setDescription(user.getDescription());
        out.setUuid(user.getUuid());

        return out;
    }

    public User updateUser(String userId, User user){

        User getData = userRepository.findOne(userId);

        if(getData == null) {
            return null;
        }
        getData.setName(user.getName());
        getData.setEmail(user.getEmail());
        getData.setPhone(user.getPhone());
        getData.setChatId(user.getChatId());
        getData.setDescription(user.getDescription());
        getData.setUuid(user.getUuid());

        User updateData = userRepository.save(getData);

        return updateData;
    }

    public void deleteUser(String userId) {

        userRepository.delete(userId);
    }



}
