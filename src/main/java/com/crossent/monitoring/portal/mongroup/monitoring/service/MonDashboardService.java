package com.crossent.monitoring.portal.mongroup.monitoring.service;

import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerTitleMap;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.ServerStatusesResDto;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.StatisticsResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonDashboardService {
    @Autowired
    ApplicationProperties applicationProperties;



//서버 통계 정보

    public StatisticsResVo getServerStatistics(Integer monGroupId){
        return null;
    }

//서버 리스트
//public List<ServerStatusesResDto> pageServerStatuses(Integer monitoringGroupId) {
//
//    String key = null;
//    String keyword = null;
//    Map<String, Map<String, String>> influxQueryFilters = applicationProperties.getInfluxQueryFilters();
//
//    Map<String, String> keywords = search.getKeywords();
//    if (keywords != null) {
//        Iterator<String> keys = keywords.keySet().iterator();
//        while (keys.hasNext()) {
//            key = keys.next();
//            keyword = keywords.get(key);
//            logger.debug("keyword ::::: " + keyword);
//            keyword = "%" + keyword + "%";
//        }
//    }
//
//    Page<MgServer> pageMgServer = null;
//    logger.debug("key : {}", key);
//    logger.debug("keyword : {}", keyword);
//
//    if (key == null) {
//        if(forDashboard){
//            pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndDashboardYn(paging.toPagingRequest(), monitoringGroupId, "Y", "Y");
//        }else {
//            pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId, "Y");
//        }
//    } else {
//
//        switch (key) {
//            case "hostName": {
//                if(forDashboard) {
//                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndDashboardYnAndServerResource_HostNameLike(paging.toPagingRequest(), monitoringGroupId, "Y", "Y", keyword);
//                }else {
//                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndServerResource_HostNameLike(paging.toPagingRequest(), monitoringGroupId, "Y", keyword);
//                }
//            }
//            break;
//            case "serverName": {
//                if(forDashboard){
//                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndDashboardYnAndServerResource_NameLike(paging.toPagingRequest(), monitoringGroupId, "Y", "Y", keyword);
//                }else{
//                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndServerResource_NameLike(paging.toPagingRequest(), monitoringGroupId, "Y", keyword);
//                }
//            }
//            break;
//            default:
//                throw new BusinessException("unDefSearchKey", key);
//        }
//    }
//
//    PagingResVo<ServerStatusesResDto> pageServerStatusesResDtoPagingResVo = new PagingResVo<ServerStatusesResDto>(pageMgServer, false);
//
//    List<MgServer> mgServers = pageMgServer.getContent();
//
//
//    if (logger.isDebugEnabled()) {
//        logger.debug("mgServers : {}", mgServers);
//    }
//
//    //logger.debug("mgServer {} ::", mgServers);
//    Collection<MgServerTitleMap> mgServerTitleMaps = mgServerTitleMapRepository.findAllByMonGroupId(monitoringGroupId);
//
//    //title셋팅
//    for (MgServerTitleMap titleMap : mgServerTitleMaps) {
//
//        pageServerStatusesResDtoPagingResVo.addTile(titleMap.getMeasurements().getName());
//
//    }
//
//    List<ServerResource> serverResources = new ArrayList<ServerResource>();
//    for (MgServer mgServer : mgServers) {
//        serverResources.add(mgServer.getServerResource());
//    }
//
//    List<ServerStatusesResDto> serverStatusesResDtos = monCommonService.pageServerStatuses(monitoringGroupId, mgServerTitleMaps, serverResources);
//    pageServerStatusesResDtoPagingResVo.setList(serverStatusesResDtos);
//
//    return pageServerStatusesResDtoPagingResVo;
//}


//서버 그룹 리스트

//어플리케이션 통계 정보

    public StatisticsResVo getAppStatistics(Integer monGroupId){
        return null;
    }

//어플리케이션 리스트

//어플리케이션 그룹 리스트

//이벤트 리스트

}
