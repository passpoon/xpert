package com.crossent.monitoring.portal.mongroup.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import com.crossent.monitoring.portal.jpa.repository.*;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgAppGroupDto;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerGroupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationGroupService {
    private static Logger logger = LoggerFactory.getLogger(ApplicationGroupService.class);

    @Autowired
    MgAppGroupRepository mgAppGroupRepository;

    @Autowired
    AppInfoRepository appInfoRepository;

    @Autowired
    AppInfoCriticalValueRepository appInfoCriticalValueRepository;

    @Autowired
    MgAppGroupCriticalValueRepository mgAppGroupCriticalValueRepository;

    @Autowired
    MgAppGroupAppRepository mgAppGroupAppRepository;

    public PagingResVo<MgAppGroupDto> pagingAppGroup(Integer monitoringGroupId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Page<MgAppGroup> allByMonGroupId = mgAppGroupRepository.findAllByMonGroupId(pagingReqVo.toPagingRequest(), monitoringGroupId);

        PagingResVo<MgAppGroupDto> resPage = new PagingResVo<MgAppGroupDto>(allByMonGroupId, false);

        List<MgAppGroup> content = allByMonGroupId.getContent();
        List<MgAppGroupDto> mgAppGroupDtos = new ArrayList<MgAppGroupDto>();
        for(MgAppGroup mgAppGroup : content){
            MgAppGroupDto mgAppGroupDto = new MgAppGroupDto();
            mgAppGroupDto.setMonGroupId(mgAppGroup.getMonGroup().getId());
            mgAppGroupDto.setId(mgAppGroup.getId());
            mgAppGroupDto.setName(mgAppGroup.getName());
            mgAppGroupDto.setAppInfoId(mgAppGroup.getAppInfo().getId());
            mgAppGroupDto.setAppInfoName(mgAppGroup.getAppInfo().getName());
            mgAppGroupDto.setDescription(mgAppGroup.getDescription());
            mgAppGroupDto.setMonitoringYn(mgAppGroup.getMonitoringYn());
            mgAppGroupDto.setDashboardYn(mgAppGroup.getDashboardYn());

            mgAppGroupDtos.add(mgAppGroupDto);
        }
        resPage.setList(mgAppGroupDtos);

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
        Page<MgAppGroup> mgAppGroups = null;
        if (key == null) {
            //TODO 전체조회
            mgAppGroups = mgAppGroupRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "name": {
                    mgAppGroups = mgAppGroupRepository.findAllByMonGroupIdAndNameLike(monitoringGroupId, pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description": {
                    mgAppGroups = mgAppGroupRepository.findAllByMonGroupIdAndDescriptionLike(monitoringGroupId, pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        return resPage;
    }

    public void createAppGroup(Integer monitoringGroupId, MgAppGroup mgAppGroup) {

        MgAppGroup mg = new MgAppGroup();
        mg.setName(mgAppGroup.getName());
        mg.setMonGroupId(monitoringGroupId);
        mg.setAppInfoId(mgAppGroup.getAppInfoId());
        mg.setDescription(mgAppGroup.getDescription());
        mg.setDashboardYn(mgAppGroup.getDashboardYn());
        mg.setMonitoringYn(mgAppGroup.getMonitoringYn());

        MgAppGroup result = mgAppGroupRepository.save(mg);

        AppInfo appInfo = appInfoRepository.findById(result.getAppInfoId());
        Collection<Measurement> measurements = appInfo.getMeasurements();
        for(Measurement measurement : measurements) {
            Collection<Metric> metrics = measurement.getMetrics();
            for(Metric metric : metrics) {
                Integer id = metric.getId();

                AppInfoCriticalValuePK appInfoCriticalValuePK = new AppInfoCriticalValuePK();

                appInfoCriticalValuePK.setAppInfoId(appInfo.getId());
                appInfoCriticalValuePK.setMeasurementId(measurement.getId());
                appInfoCriticalValuePK.setMetricId(id);

                AppInfoCriticalValue appInfoCriticalValue = appInfoCriticalValueRepository.findOne(appInfoCriticalValuePK);
                if(appInfoCriticalValue != null) {
                    MgAppGroupCriticalValue mgAppGroupCriticalValue = new MgAppGroupCriticalValue();
                    mgAppGroupCriticalValue.setMgAppGroupId(mgAppGroup.getId());
                    mgAppGroupCriticalValue.setMetricId(id);
                    mgAppGroupCriticalValue.setWarning(appInfoCriticalValue.getWarning());
                    mgAppGroupCriticalValue.setCritical(appInfoCriticalValue.getCritical());

                    mgAppGroupCriticalValueRepository.save(mgAppGroupCriticalValue);
                }
            }
        }
    }

    public MgAppGroupDto getAppGroup(Integer monitoringGroupId, Integer appGroupId) {

        MgAppGroup mgAppGroup = mgAppGroupRepository.findByMonGroupIdAndId(monitoringGroupId, appGroupId);

        MgAppGroupDto mgAppGroupDto = new MgAppGroupDto();
        mgAppGroupDto.setId(appGroupId);
        mgAppGroupDto.setMonGroupId(monitoringGroupId);
        mgAppGroupDto.setName(mgAppGroup.getName());
        mgAppGroupDto.setAppInfoId(mgAppGroup.getAppInfoId());
        mgAppGroupDto.setAppInfoName(mgAppGroup.getAppInfo().getName());
        mgAppGroupDto.setDescription(mgAppGroup.getDescription());
        mgAppGroupDto.setDashboardYn(mgAppGroup.getDashboardYn());
        mgAppGroupDto.setMonitoringYn(mgAppGroup.getMonitoringYn());

        return mgAppGroupDto;
    }

    public MgAppGroup updateAppGroup(Integer monitoringGroupId, Integer appGroupId, MgAppGroup mgAppGroup) {

        MgAppGroup updateAppGroup = mgAppGroupRepository.findByMonGroupIdAndId(monitoringGroupId, appGroupId);

        updateAppGroup.setName(mgAppGroup.getName());
        updateAppGroup.setAppInfoId(mgAppGroup.getAppInfoId());
        updateAppGroup.setMonitoringYn(mgAppGroup.getMonitoringYn());
        updateAppGroup.setDashboardYn(mgAppGroup.getDashboardYn());

        MgAppGroup updateData = mgAppGroupRepository.save(updateAppGroup);

        return updateData;
    }

    public void deleteAppGroups(Integer monitoringGroupId, Integer[] appGroupIds) {

        mgAppGroupRepository.deleteByMonGroupIdAndIdIn(monitoringGroupId, appGroupIds);
    }

    public Collection<MgAppGroupCriticalValue> getAppGroupMetrics(Integer monitoringGroupId, Integer appGroupId) {

        MgAppGroup mgAppGroup = mgAppGroupRepository.findById(appGroupId);
        Collection<MgAppGroupCriticalValue> mgAppGroupCriticalValues = mgAppGroup.getMgAppGroupCriticalValues();

        return mgAppGroupCriticalValues;
    }

    public MgAppGroupCriticalValue updateAppGroupMetrics(Integer monitoringGroupId, Integer appGroupId, Integer metricId, MgAppGroupCriticalValue mgAppGroupCriticalValue) {

        MgAppGroupCriticalValue updateAppGroupMetric = mgAppGroupCriticalValueRepository.findByMgAppGroupIdAndMetricId(appGroupId, metricId);

        updateAppGroupMetric.setWarning(mgAppGroupCriticalValue.getWarning());
        updateAppGroupMetric.setCritical(mgAppGroupCriticalValue.getCritical());

        MgAppGroupCriticalValue updateData = mgAppGroupCriticalValueRepository.save(updateAppGroupMetric);

        return updateData;
    }

    public Collection<MgApp> getAppGroupAppResource(Integer monitoringGroupId, Integer appGroupId) {

        MgAppGroup mgAppGroup = mgAppGroupRepository.findByMonGroupIdAndId(monitoringGroupId, appGroupId);
        Collection<MgApp> mgApps = mgAppGroup.getMgApps();

        return mgApps;
    }

    public void insertAppGroupAppResources(Integer monitoringGroupId, Integer appGroupId, Integer[] appResourceIds) {

        for(Integer appResourceId : appResourceIds) {
            MgAppGroupApp map = new MgAppGroupApp();
            map.setMonGroupId(monitoringGroupId);
            map.setAppGroupId(appGroupId);
            map.setAppResourceId(appResourceId);

            MgAppGroupApp result = mgAppGroupAppRepository.save(map);
        }
    }

    public void deleteAppGroupAppResources(Integer monitoringGroupId, Integer appGroupId, Integer[] appResourceIds) {

        mgAppGroupAppRepository.deleteByMonGroupIdAndAppGroupIdAndAppResourceIdIn(monitoringGroupId, appGroupId, appResourceIds);
    }

    public void deleteAppGroupAppResource(Integer monitoringGroupId, Integer appGroupId, Integer appResourceId) {

        mgAppGroupAppRepository.deleteByMonGroupIdAndAppGroupIdAndAppResourceId(monitoringGroupId, appGroupId, appResourceId);
    }
}
