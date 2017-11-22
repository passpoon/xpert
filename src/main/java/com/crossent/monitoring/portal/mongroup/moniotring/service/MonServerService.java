package com.crossent.monitoring.portal.mongroup.moniotring.service;

import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.repository.MgServerRepository;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MonServerService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MonServerService.class);

    @Value("${monitoring.server.measurements}")
    public String[] measurement;

    @Autowired
    MgServerRepository mgServerRepository;

    /*public void getServerStatuses(Integer monitoringGroupId) {

        List<MgServer> mgServers = mgServerRepository.findByMonGroupId(monitoringGroupId);

        logger.debug("mgServer {} ::", mgServers);


    }*/
}
