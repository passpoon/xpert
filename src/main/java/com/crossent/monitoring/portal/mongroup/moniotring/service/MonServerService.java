package com.crossent.monitoring.portal.mongroup.moniotring.service;

import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerTitleMap;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.repository.MeasurementRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerCriticalValueRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerTitleMapRepository;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerStatusesResDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonServerService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MonServerService.class);

    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgServerTitleMapRepository mgServerTitleMapRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MgServerCriticalValueRepository mgServerCriticalValueRepository;


    @Autowired
    private ApplicationProperties applicationProperties;


    @Autowired
    private MonCommonService monCommonService;


    public PagingResVo<ServerStatusesResDto> pageServerStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search) {

        String key = null;
        String keyword = null;
        Map<String, Map<String, String>> influxQueryFilters = applicationProperties.getInfluxQueryFilters();

        Map<String, String> keywords = search.getKeywords();
        if (keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                logger.debug("keyword ::::: " + keyword);
                keyword = "%" + keyword + "%";
            }
        }

        Page<MgServer> pageMgServer = null;
        logger.debug("key : {}", key);
        logger.debug("keyword : {}", keyword);

        if (key == null) {
            pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId, "Y");
        } else {

            switch (key) {
                case "hostName": {
                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndServerResource_HostNameLike(paging.toPagingRequest(), monitoringGroupId, "Y", keyword);
                }
                break;
                case "serverName": {
                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndServerResource_NameLike(paging.toPagingRequest(), monitoringGroupId, "Y", keyword);
                }
                break;
                default:
                    throw new BusinessException("unDefSearchKey", key);
            }
        }

        PagingResVo<ServerStatusesResDto> pageServerStatusesResDtoPagingResVo = new PagingResVo<ServerStatusesResDto>(pageMgServer, false);

        List<MgServer> mgServers = pageMgServer.getContent();


        if (logger.isDebugEnabled()) {
            logger.debug("mgServers : {}", mgServers);
        }

        //logger.debug("mgServer {} ::", mgServers);
        Collection<MgServerTitleMap> mgServerTitleMaps = mgServerTitleMapRepository.findAllByMonGroupId(monitoringGroupId);

        //title셋팅
        for (MgServerTitleMap titleMap : mgServerTitleMaps) {

            pageServerStatusesResDtoPagingResVo.addTile(titleMap.getMeasurements().getName());

        }

        List<ServerResource> serverResources = new ArrayList<ServerResource>();
        for (MgServer mgServer : mgServers) {
            serverResources.add(mgServer.getServerResource());
        }

        List<ServerStatusesResDto> serverStatusesResDtos = monCommonService.pageServerStatuses(monitoringGroupId, mgServerTitleMaps, serverResources);
        pageServerStatusesResDtoPagingResVo.setList(serverStatusesResDtos);

        return pageServerStatusesResDtoPagingResVo;
    }


}

