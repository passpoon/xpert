package com.crossent.monitoring.portal.mongroup.monitoring.service;


import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.vo.SearchVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.MgAppRepository;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.AppStatusesResDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonApplicationService {

    private Logger logger = LoggerFactory.getLogger(MonApplicationService.class);

    @Autowired
    MgAppRepository mgAppRepository;

    @Autowired
    MonCommonService monCommonService;

    public PagingResVo<AppStatusesResDto> pageAppStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search){
        return pageAppStatuses(monitoringGroupId, paging, search, false);

    }

    public PagingResVo<AppStatusesResDto> pageAppStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search, boolean dashboardYn) {

        String key = null;
        String keyword = null;
        Map<String, Map<String, String>> influxQueryFilters = ApplicationProperties.influxQueryFilters;

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

        SearchVo searchVo = new SearchVo(search);


        Page<MgApp> pageMgApp = null;
        logger.debug("key : {}", key);
        logger.debug("keyword : {}", keyword);

        if (!searchVo.isHaveKeyworkd()) {
            if(dashboardYn){
                pageMgApp = mgAppRepository.findAllByMonGroupIdAndMonitoringYnAndDashboardYn(paging.toPagingRequest(), monitoringGroupId, "Y", "Y");

            }else {
                pageMgApp = mgAppRepository.findAllByMonGroupIdAndMonitoringYn(paging.toPagingRequest(), monitoringGroupId, "Y");
            }
        } else {

            switch (key) {
                case "hostName": {
                    if(dashboardYn) {
                        pageMgApp = mgAppRepository.findAllByMonGroupIdAndMonitoringYnAndDashboardYnAndAppResource_ServerResource_HostNameLike(paging.toPagingRequest(), monitoringGroupId, "Y","Y", keyword);
                    }else{
                        pageMgApp = mgAppRepository.findAllByMonGroupIdAndMonitoringYnAndAppResource_ServerResource_HostNameLike(paging.toPagingRequest(), monitoringGroupId, "Y", keyword);

                    }
                }
                break;
                case "appName": {
                    if(dashboardYn) {
                        pageMgApp = mgAppRepository.findAllByMonGroupIdAndMonitoringYnAndDashboardYnAndAppResource_NameLike(paging.toPagingRequest(), monitoringGroupId, "Y","Y", keyword);
                    }else{
                        pageMgApp = mgAppRepository.findAllByMonGroupIdAndMonitoringYnAndAppResource_NameLike(paging.toPagingRequest(), monitoringGroupId, "Y", keyword);

                    }
                }
                break;
                default:
                    throw new BusinessException("unDefSearchKey", key);
            }
        }

        PagingResVo<AppStatusesResDto> pageAppStatusesResDtoPagingResVo = new PagingResVo<AppStatusesResDto>(pageMgApp, false);

        List<MgApp> mgApps = pageMgApp.getContent();


        if (logger.isDebugEnabled()) {
            logger.debug("mgApps : {}", mgApps);
        }


        List<AppResource> appResources = new ArrayList<AppResource>();
        for (MgApp mgApp : mgApps) {
            appResources.add(mgApp.getAppResource());
        }

        List<AppStatusesResDto> appStatusesResDtos = monCommonService.pageAppStatuses(monitoringGroupId, appResources);

        pageAppStatusesResDtoPagingResVo.setList(appStatusesResDtos);


        return pageAppStatusesResDtoPagingResVo;
    }

}
