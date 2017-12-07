package com.crossent.monitoring.portal.mongroup.moniotring.service;

import com.crossent.monitoring.portal.common.constants.*;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.elasticsearch.ElasticsearchTemplate;
import com.crossent.monitoring.portal.common.lib.util.DateUtil;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.lib.util.StringUtil;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.*;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.mongroup.moniotring.dao.MonServerDao;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.*;
import com.crossent.monitoring.portal.mongroup.moniotring.util.MonitoringUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.crossent.monitoring.portal.common.lib.util.DateUtil.DATE_HMS_PATTERN;
import static com.crossent.monitoring.portal.common.lib.util.DateUtil.TIMESTAMP_T_PATTERN;

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
    private MonServerDao monServerDao;

    @Autowired
    private ServerResourceRepository serverResourceRepository;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private EventHistoryRepository eventHistoryRepository;



    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private MonCommonService monCommonService;



    public PagingResVo<ServerStatusesResDto> pageServerStatuses(Integer monitoringGroupId, PagingReqVo paging, SearchReqVo search) {

        String key = null;
        String keyword = null;
        Map<String, Map<String, String>> influxQueryFilters = applicationProperties.getInfluxQueryFilters();

        Map<String, String> keywords = search.getKeywords();
        if (keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while(keys.hasNext()) {
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
                    pageMgServer = mgServerRepository.findAllByMonGroupIdAndMonitoringYnAndServerResource_NameLike(paging.toPagingRequest(),  monitoringGroupId,"Y" , keyword);
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

        logger.debug("mgServer {} ::", mgServers);
        Collection<MgServerTitleMap> mgServerTitleMaps = mgServerTitleMapRepository.findAllByMonGroupId(monitoringGroupId);

        //title셋팅
        for(MgServerTitleMap titleMap : mgServerTitleMaps){

            pageServerStatusesResDtoPagingResVo.addTile(titleMap.getMeasurements().getName());

        }

        List<ServerResource> serverResources = new ArrayList<ServerResource>();
        for(MgServer mgServer : mgServers){
            serverResources.add(mgServer.getServerResource());
        }

        List<ServerStatusesResDto> serverStatusesResDtos = monCommonService.pageServerStatuses(monitoringGroupId, mgServerTitleMaps, serverResources);
        pageServerStatusesResDtoPagingResVo.setList(serverStatusesResDtos);

        return pageServerStatusesResDtoPagingResVo;
    }










}

