package com.crossent.monitoring.portal.group.mon.service;

import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.repository.MonGroupRepository;
import com.crossent.monitoring.portal.jpa.repository.UserGroupRepository;
import com.crossent.monitoring.portal.jpa.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import org.slf4j.Logger;

@Service
public class MonGroupService {
    Logger logger = LoggerFactory.getLogger(MonGroupService.class);

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    MonGroupRepository monGroupRepository;

    @Autowired
    UserRepository userRepository;

    public Collection<MonGroup> listMonGroup(String userId){

        User user = userRepository.findOne(userId);
        Collection<MonGroup> monGroups = user.getMonGroups();
        logger.debug("monGroups : {}", monGroups);
        return monGroups;
    }
}
