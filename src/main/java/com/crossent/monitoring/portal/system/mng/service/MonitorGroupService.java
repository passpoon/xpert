package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.constants.CodeConstants;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.system.mng.dto.MgUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Service
public class MonitorGroupService {
    private static Logger logger = LoggerFactory.getLogger(MonitorGroupService.class);

    @Autowired
    MonGroupRepository monGroupRepository;

    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgAppRepository mgAppRepository;

    @Autowired
    MgUserRepository mgUserRepository;

    @Autowired
    TypeCodeRepository typeCodeRepository;

    @Autowired
    ServerTypeCriticalValueRepository serverTypeCriticalValueRepository;

    @Autowired
    MgServerCriticalValueRepository mgServerCriticalValueRepository;

    @Autowired
    ServerResourceRepository serverResourceRepository;

    @Autowired
    AppResourceRepository appResourceRepository;

    @Autowired
    AppInfoCriticalValueRepository appInfoCriticalValueRepository;

    @Autowired
    MgAppCriticalValueRepository mgAppCriticalValueRepository;


    public PagingResVo<MonGroup> pagingMonGroup(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if (keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<MonGroup> monGroups = null;
        if (key == null) {
            //TODO 전체조회
            monGroups = monGroupRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "name": {
                    monGroups = monGroupRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description": {
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

        if (getData == null) {
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

    public void insertMonGroupServers(Integer monitoringGroupId, Integer[] serverResourceIds) {

        // 다른 데이터가 null이기 때문에 추후에 모니터링 영역에서 셋팅하는 부분임
        for (Integer resourceId : serverResourceIds) {
            MgServer mgServer = new MgServer();
            mgServer.setMonGroupId(monitoringGroupId);
            mgServer.setServerResourceId(resourceId);

            MgServer groupMap = mgServerRepository.save(mgServer);
            logger.debug("groupMap :: {}", groupMap);

//          ServerResource serverResource = groupMap.getServerResource();
            ServerResource serverResource = serverResourceRepository.findById(groupMap.getServerResourceId());
            ServerType serverType = serverResource.getServerType();
            Collection<Measurement> measurements = serverType.getMeasurements();
            for(Measurement measurement : measurements) {
                Collection<Metric> metrics = measurement.getMetrics();
                for(Metric metric : metrics) {
                    Integer id = metric.getId();

                    ServerTypeCriticalValuePK serverTypeCriticalValuePK = new ServerTypeCriticalValuePK();

                    serverTypeCriticalValuePK.setServerTypeId(serverType.getId());
                    serverTypeCriticalValuePK.setMeasurementId(measurement.getId());
                    serverTypeCriticalValuePK.setMetricId(id);

                    ServerTypeCriticalValue serverTypeCriticalValue = serverTypeCriticalValueRepository.findOne(serverTypeCriticalValuePK);
                    if(serverTypeCriticalValue != null) {
                        MgServerCriticalValue mgServerCriticalValue = new MgServerCriticalValue();
                        mgServerCriticalValue.setMonGroupId(monitoringGroupId);
                        mgServerCriticalValue.setServerResourceId(resourceId);
                        mgServerCriticalValue.setMetricId(id);
                        mgServerCriticalValue.setWarning(serverTypeCriticalValue.getWarning());
                        mgServerCriticalValue.setCritical(serverTypeCriticalValue.getCritical());
                        mgServerCriticalValueRepository.save(mgServerCriticalValue);
                    }
                }
            }
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

    public void insertMonGroupApps(Integer monitoringGroupId, Integer[] appResourceIds) {

        for (Integer appId : appResourceIds) {
            MgApp mgApp = new MgApp();
            mgApp.setMonGroupId(monitoringGroupId);
            mgApp.setAppResourceId(appId);

            MgApp groupMap = mgAppRepository.save(mgApp);

            AppResource appResource = appResourceRepository.findById(groupMap.getAppResourceId());
            AppInfo appInfo = appResource.getAppInfo();
            Collection<Measurement> measurements = appInfo.getMeasurements();
            for (Measurement measurement : measurements) {
                Collection<Metric> metrics = measurement.getMetrics();
                for (Metric metric : metrics) {
                    Integer id = metric.getId();

                    AppInfoCriticalValuePK appInfoCriticalValuePK = new AppInfoCriticalValuePK();

                    appInfoCriticalValuePK.setAppInfoId(appInfo.getId());
                    appInfoCriticalValuePK.setMeasurementId(measurement.getId());
                    appInfoCriticalValuePK.setMetricId(id);

                    AppInfoCriticalValue appInfoCriticalValue = appInfoCriticalValueRepository.findOne(appInfoCriticalValuePK);
                    if (appInfoCriticalValue != null) {
                        MgAppCriticalValue mgAppCriticalValue = new MgAppCriticalValue();
                        mgAppCriticalValue.setMonGroupId(monitoringGroupId);
                        mgAppCriticalValue.setAppResourceId(appId);
                        mgAppCriticalValue.setMetricId(id);
                        mgAppCriticalValue.setWarning(appInfoCriticalValue.getWarning());
                        mgAppCriticalValue.setCritical(appInfoCriticalValue.getCritical());
                        mgAppCriticalValueRepository.save(mgAppCriticalValue);
                    }
                }
            }
        }
    }

    public void deleteMonGroupApps(Integer monitoringGroupId, Integer[] appResourceIds) {

        mgAppRepository.deleteByMonGroupIdAndAppResourceIdIn(monitoringGroupId, appResourceIds);
    }

    public void deleteMonGroupApp(Integer monitoringGroupId, Integer appResourceIds) {

        mgAppRepository.deleteByMonGroupIdAndAppResourceId(monitoringGroupId, appResourceIds);
    }

    // 관리자
    public Collection<MgUserDto> getMonGroupManagers(Integer monitoringGroupId) {

        Collection<MgUser> mgUsers = mgUserRepository.findAllByMonGroupIdAndTypeCodeCode(monitoringGroupId, CodeConstants.MG_GROUP_MANAGER);

        Collection<MgUserDto> mgUserDtos = new ArrayList<MgUserDto>();

        for (MgUser mgUser : mgUsers) {
            MgUserDto mgUserDto = new MgUserDto();
            mgUserDto.setUserId(mgUser.getUserId());
            logger.debug("mgUserDto.setUserId(mgUser.getUserId()) :: {}", mgUser.getUserId());
            mgUserDto.setUserName(mgUser.getUser().getName());

            logger.debug("mgUserDto :: {}", mgUserDto);
            mgUserDtos.add(mgUserDto);
        }
        return mgUserDtos;
    }

    public void insertMonGroupManagers(Integer monitoringGroupId, String[] userIds){

        for(String userId : userIds) {
            MgUser mgUser = new MgUser();
            mgUser.setMonGroupId(monitoringGroupId);
            mgUser.setUserId(userId);
            mgUser.setTypeCodeCode(CodeConstants.MG_GROUP_MANAGER);

            MgUser groupMap = mgUserRepository.save(mgUser);
        }
    }

    public void deleteMonGroupManagers(Integer monitoringGroupId, String[] userIds) {

        mgUserRepository.deleteByMonGroupIdAndUserIdIn(monitoringGroupId, userIds);
    }

    public void deleteMonGroupManager(Integer monitoringGroupId, String userId) {

        mgUserRepository.deleteByMonGroupIdAndUserId(monitoringGroupId, userId);
    }

    // 운영자
    public Collection<MgUserDto> getMonGroupOperators(Integer monitoringGroupId) {

        Collection<MgUser> mgUsers = mgUserRepository.findAllByMonGroupIdAndTypeCodeCode(monitoringGroupId, CodeConstants.MG_GROUP_OPERRATOR);

        Collection<MgUserDto> mgUserDtos = new ArrayList<MgUserDto>();

        for (MgUser mgUser : mgUsers) {
            MgUserDto mgUserDto = new MgUserDto();
            mgUserDto.setUserId(mgUser.getUserId());
            mgUserDto.setUserName(mgUser.getUser().getName());

            mgUserDtos.add(mgUserDto);
        }
        return mgUserDtos;
    }

    public void insertMonGroupOperators(Integer monitoringGroupId, String[] userIds){

        for(String userId : userIds) {
            MgUser mgUser = new MgUser();
            mgUser.setMonGroupId(monitoringGroupId);
            mgUser.setUserId(userId);
            mgUser.setTypeCodeCode(CodeConstants.MG_GROUP_OPERRATOR);

            MgUser groupMap = mgUserRepository.save(mgUser);
        }
    }

    public void deleteMonGroupOperators(Integer monitoringGroupId, String[] userIds) {

        mgUserRepository.deleteByMonGroupIdAndUserIdIn(monitoringGroupId, userIds);
    }

    public void deleteMonGroupOperator(Integer monitoringGroupId, String userId) {

        mgUserRepository.deleteByMonGroupIdAndUserId(monitoringGroupId, userId);
    }
}
