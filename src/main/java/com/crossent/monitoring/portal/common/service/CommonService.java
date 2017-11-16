package com.crossent.monitoring.portal.common.service;

import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class CommonService {

    @Autowired
    MonGroupRepository monGroupRepository;

    @Autowired
    ServerResourceRepository serverResourceRepository;

    @Autowired
    AppResourceRepository appResourceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServerTypeRepository serverTypeRepository;


    /*// 모니터링 그룹(사용자 조회)
    public Collection<User> getMonGroupUsers(Integer monitoringGroupId, String name) {

        Collection<User> users = userRepository.findAllByNameLikeAndMonGroups_Id("%"+name+"%", monitoringGroupId);
        logger.debug("user : {} ", users);
        return users;
    }*/

    /*// 서버유형
    public Collection<ServerType> getServerResourceServerTypes(Integer serverId, String name) {

        Collection<ServerType> serverTypes = serverTypeRepository.findAllByNameLikeAndServerResources_Id("%"+name+"%", serverId);

        return null;
    }*/






}
