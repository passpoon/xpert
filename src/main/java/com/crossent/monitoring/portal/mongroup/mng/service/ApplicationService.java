package com.crossent.monitoring.portal.mongroup.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgApp;
import com.crossent.monitoring.portal.jpa.domain.MgAppCriticalValue;
import com.crossent.monitoring.portal.jpa.repository.MgAppCriticalValueRepository;
import com.crossent.monitoring.portal.jpa.repository.MgAppRepository;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgAppDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService {

    @Autowired
    MgAppRepository mgAppRepository;

    @Autowired
    MgAppCriticalValueRepository mgAppCriticalValueRepository;

    public PagingResVo<MgAppDto> pagingServer(Integer monitoringGroupId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Page<MgApp> allMonGroupId = mgAppRepository.findAllByMonGroupId(pagingReqVo.toPagingRequest(), monitoringGroupId);

        PagingResVo<MgAppDto> resPage = new PagingResVo<MgAppDto>(allMonGroupId, false);

        List<MgApp> content = allMonGroupId.getContent();
        List<MgAppDto> mgAppDtos = new ArrayList<MgAppDto>();
        for(MgApp mgApp : content){
            MgAppDto mgAppDto = new MgAppDto();
            mgAppDto.setAppResourceId(mgApp.getAppResourceId());
            mgAppDto.setApplicationName(mgApp.getAppResource().getName());
            mgAppDto.setMonitoringYn(mgApp.getMonitoringYn());
            mgAppDto.setDashboardYn(mgApp.getDashboardYn());

            mgAppDtos.add(mgAppDto);
        }
        resPage.setList(mgAppDtos);

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
        Page<MgApp> mgApps = null;
        if (key == null) {
            //TODO 전체조회
            mgApps = mgAppRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "applicationName": {
                    mgApps = mgAppRepository.findAllByMonGroupIdAndAppResource_NameLike(monitoringGroupId, pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        return resPage;
    }

    public MgApp updateApp(Integer monitoringGroupId, Integer appResourceId, MgApp mgApp) {

        MgApp updateApp = mgAppRepository.findByMonGroupIdAndAppResourceId(monitoringGroupId, appResourceId);

        updateApp.setMonitoringYn(mgApp.getMonitoringYn());
        updateApp.setDashboardYn(mgApp.getDashboardYn());

        MgApp updateData = mgAppRepository.save(updateApp);

        return updateData;
    }

    public Collection<MgAppCriticalValue> getAppMetrics(Integer monitoringGroupId, Integer appResourceId) {

        MgApp mgApp = mgAppRepository.findByMonGroupIdAndAppResourceId(monitoringGroupId, appResourceId);

        Collection<MgAppCriticalValue> mgAppCriticalValues = mgApp.getMgAppCriticalValues();

        return mgAppCriticalValues;
    }

    public MgAppCriticalValue updateAppMetrics(Integer monitoringGroupId, Integer appResourceId, Integer metricId, MgAppCriticalValue mgAppCriticalValue) {

        MgAppCriticalValue updateAppMetric = mgAppCriticalValueRepository.findByMonGroupIdAndAppResourceIdAndMetricId(monitoringGroupId, appResourceId, metricId);

        updateAppMetric.setWarning(mgAppCriticalValue.getWarning());
        updateAppMetric.setCritical(mgAppCriticalValue.getCritical());

        return updateAppMetric;
    }
}
