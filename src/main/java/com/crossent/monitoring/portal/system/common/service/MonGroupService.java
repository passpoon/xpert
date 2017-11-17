package com.crossent.monitoring.portal.system.common.service;

import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.repository.UserRepository;
import com.crossent.monitoring.portal.system.common.dto.MonGroupDto;
import com.crossent.monitoring.portal.system.common.dto.UserDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MonGroupService {
    Logger logger = LoggerFactory.getLogger(MonGroupService.class);

    @Autowired
    UserRepository userRepository;

    public Collection<MonGroup> listMonGroup(String userId) {

        User user = userRepository.findOne(userId);

        Collection<MonGroup> monGroups = user.getMonGroups();
        logger.debug("monGroups : {}", monGroups);
        return monGroups;
    }

    public UserDto listUsers(String uuid) {

        User user = userRepository.findByUuid(uuid);
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setChatId(user.getChatId());
        userDto.setDescription(user.getDescription());
        userDto.setUuid(user.getUuid());


        /*List<MetricDto> metricList = new ArrayList<MetricDto>();
        Collection<MonGroupDto> monGroupList = user.getMonGroups();*/
        List<MonGroupDto> monGroups = new ArrayList<>();


        Collection<MonGroup> groups = user.getMonGroups();

        for(MonGroup group : groups){
            MonGroupDto monGroupDto = new MonGroupDto();
            monGroupDto.setId(group.getId());
            monGroupDto.setName(group.getName());
            monGroupDto.setDescription(group.getDescription());

            monGroups.add(monGroupDto);

        }
        userDto.setMonGroups(monGroups);

        return userDto;
    }


}
