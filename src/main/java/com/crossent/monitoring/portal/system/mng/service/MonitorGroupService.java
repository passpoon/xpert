package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.MgAppRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerRepository;
import com.crossent.monitoring.portal.jpa.repository.MonGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Service
public class MonitorGroupService {
    private static Logger logger  = LoggerFactory.getLogger(MonitorGroupService.class);

    @Autowired
    MonGroupRepository monGroupRepository;

    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgAppRepository mgAppRepository;


    public PagingResVo<MonGroup> pagingMonGroup(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

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
        Page<MonGroup> monGroups = null;
        if(key == null){
            //TODO 전체조회
            monGroups = monGroupRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "name":
                {
                    monGroups = monGroupRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description":
                {
                    monGroups = monGroupRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        PagingResVo<MonGroup> resPage = new PagingResVo<MonGroup>(monGroups, true);

        return resPage;
    }

    public void insertMonGroup(MonGroup monGroup) {

        MonGroup inMonGroup = new MonGroup();
        inMonGroup.setName(monGroup.getName());
        inMonGroup.setDescription(monGroup.getDescription());

        MonGroup resUser = monGroupRepository.save(inMonGroup);
    }

    public void deleteMonGroups(Integer[] monitoringGroupIds) {

        monGroupRepository.deleteByIdIn(monitoringGroupIds);
    }

    public MonGroup getMonGroup(Integer monitoringGroupId) {

        MonGroup monGroup = monGroupRepository.findOne(monitoringGroupId);

        MonGroup out = new MonGroup();
        out.setId(monitoringGroupId);
        out.setName(monGroup.getName());
        out.setDescription(monGroup.getDescription());

        return out;
    }

    public MonGroup updateMonGroup(Integer monitoringGroupId, MonGroup monGroup) {

        MonGroup getData = monGroupRepository.findOne(monitoringGroupId);

        if(getData == null) {
            return null;
        }
        getData.setName(monGroup.getName());
        getData.setDescription(monGroup.getDescription());

        MonGroup updateData = monGroupRepository.save(getData);

        return updateData;
    }

    public void deleteMonGroup(Integer monitoringGroupId) {

        monGroupRepository.delete(monitoringGroupId);
    }

    // 서버
    public Collection<ServerResource> getMonGroupServers(Integer monitoringGroupId) {

        MonGroup monGroup = monGroupRepository.findOne(monitoringGroupId);
        logger.debug("monGroup ID get :: {}", monGroup);
        Collection<ServerResource> serverResources = monGroup.getServerResource();
        logger.debug("serverResources ::: {}", serverResources);

        return serverResources;
    }

    public void insertMonGroupServers(Integer monitoringGroupId, Integer[] serverResourceIds){

        // 다른 데이터가 null이기 때문에 추후에 모니터링 영역에서 셋팅하는 부분임
        for(Integer resourceId : serverResourceIds) {
            MgServer mgServer = new MgServer();
            mgServer.setMonGroupId(monitoringGroupId);
            mgServer.setServerResourceId(resourceId);

            MgServer groupMap = mgServerRepository.save(mgServer);
            logger.debug("groupMap :: ", groupMap);
        }
    }

    public void deleteMonGroupServers(Integer monitoringGroupId, Integer[] serverResourceIds) {

        mgServerRepository.deleteByMonGroupIdAndServerResourceIdIn(monitoringGroupId, serverResourceIds);
    }

    public void deleteMonGroupServer(Integer monitoringGroupId, Integer serverResourceId) {

        mgServerRepository.deleteByMonGroupIdAndServerResourceId(monitoringGroupId, serverResourceId);
    }

    // 어플리케이션
    public Collection<AppResource> getMonGroupApps(Integer monitoringGroupId) {

        MonGroup monGroup = monGroupRepository.findOne(monitoringGroupId);
        Collection<AppResource> appResources = monGroup.getAppResource();

        return appResources;
    }

    public void insertMonGroupApps(Integer monitoringGroupId, Integer[] appResourceIds){

        for(Integer appId : appResourceIds) {
            MgApp mgApp = new MgApp();
            mgApp.setMonGroupId(monitoringGroupId);
            mgApp.setAppResourceId(appId);

            MgApp groupMap = mgAppRepository.save(mgApp);
        }
    }

    public void deleteMonGroupApps(Integer monitoringGroupId, Integer[] appResourceIds) {

        mgAppRepository.deleteByMonGroupIdAndAppResourceIdIn(monitoringGroupId, appResourceIds);
    }

    public void deleteMonGroupApp(Integer monitoringGroupId, Integer appResourceIds) {

        mgAppRepository.deleteByMonGroupIdAndAppResourceId(monitoringGroupId, appResourceIds);
    }

}
