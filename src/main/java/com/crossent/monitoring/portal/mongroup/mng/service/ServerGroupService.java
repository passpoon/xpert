package com.crossent.monitoring.portal.mongroup.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroup;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.jpa.repository.MgServerGroupCriticalValueRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerGroupRepository;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerGroupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServerGroupService {
    private static Logger logger = LoggerFactory.getLogger(ServerGroupService.class);

    /*@Autowired
    MgServerRepository mgServerRepository;*/

    /*@Autowired
    MgServerCriticalValueRepository mgServerCriticalValueRepository;*/

    @Autowired
    MgServerGroupRepository mgServerGroupRepository;

    @Autowired
    MgServerGroupCriticalValueRepository mgServerGroupCriticalValueRepository;

    public PagingResVo<MgServerGroupDto> pagingServerGroup(Integer monitoringGroupId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Page<MgServerGroup> allByMonGroupId = mgServerGroupRepository.findAllByMonGroupId(pagingReqVo.toPagingRequest(), monitoringGroupId);

        PagingResVo<MgServerGroupDto> resPage = new PagingResVo<MgServerGroupDto>(allByMonGroupId, false);

        List<MgServerGroup> content = allByMonGroupId.getContent();
        List<MgServerGroupDto> mgServerGroupDtos = new ArrayList<MgServerGroupDto>();
        for(MgServerGroup mgServerGroup : content){
            MgServerGroupDto mgServerGroupDto = new MgServerGroupDto();
            mgServerGroupDto.setId(mgServerGroup.getId());
            mgServerGroupDto.setMonGroupId(mgServerGroup.getMonGroup().getId());
            mgServerGroupDto.setName(mgServerGroup.getName());
            mgServerGroupDto.setServerTypeId(mgServerGroup.getServerType().getId());
            mgServerGroupDto.setDescription(mgServerGroup.getDescription());
            mgServerGroupDto.setMonitoringYn(mgServerGroup.getMonitoringYn());
            mgServerGroupDto.setDashboardYn(mgServerGroup.getDashboardYn());

            mgServerGroupDtos.add(mgServerGroupDto);
        }
        resPage.setList(mgServerGroupDtos);

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
        Page<MgServerGroup> mgServerGroups = null;
        if (key == null) {
            //TODO 전체조회
            mgServerGroups = mgServerGroupRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "name": {
                    mgServerGroups = mgServerGroupRepository.findAllByMonGroupIdAndNameLike(monitoringGroupId, pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description": {
                    mgServerGroups = mgServerGroupRepository.findAllByMonGroupIdAndDescriptionLike(monitoringGroupId, pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        return resPage;
    }

    public void createServerGroup(Integer monitoringGroupId, MgServerGroup mgServerGroup) {

        /*MgServerGroup byMonGroupId = mgServerGroupRepository.findByMonGroupId(monitoringGroupId);*/

        MgServerGroup mg = new MgServerGroup();
        mg.setName(mgServerGroup.getName());
        mg.setMonGroupId(monitoringGroupId);
        mg.setServerTypeId(mgServerGroup.getServerTypeId());
        mg.setDescription(mgServerGroup.getDescription());
        mg.setDashboardYn(mgServerGroup.getDashboardYn());
        mg.setMonitoringYn(mgServerGroup.getMonitoringYn());

        MgServerGroup result = mgServerGroupRepository.save(mg);
    }


    public MgServerGroup updateServerGroup(Integer monitoringGroupId, Integer serverGroupId, MgServerGroup mgServerGroup) {

        MgServerGroup updateServerGroup = mgServerGroupRepository.findByMonGroupIdAndId(monitoringGroupId, serverGroupId);

        updateServerGroup.setMonitoringYn(mgServerGroup.getMonitoringYn());
        updateServerGroup.setDashboardYn(mgServerGroup.getDashboardYn());

        MgServerGroup updateData = mgServerGroupRepository.save(updateServerGroup);

        return updateData;
    }

    /*public Collection<MgServerGroupCriticalValue> getServerGroupMetrics(Integer monitoringGroupId, Integer serverGroupId) {

        MgServerGroupCriticalValue id = mgServerGroupCriticalValueRepository.findByMonGroupIdAndServerGroupId(monitoringGroupId, serverGroupId);

        MgServerGroupCriticalValue out = new MgServerGroupCriticalValue();
        out.setMetricId(id.getMetricId());
        out.setWarning(id.getWarning());
        out.setCritical(id.getCritical());

        MgServerGroup mgServerGroup = mgServerGroupRepository.findByMonGroupIdAndId(monitoringGroupId, serverGroupId);

        return null;
        Collection<MgServerGroupCriticalValue> mgServerCriticalValues = mgServerGroup.g // 테스트

        return mgServerCriticalValues;
    }*/

    /*public MgServerCriticalValue updateServerMetrics(Integer monitoringGroupId, Integer serverResourceId, Integer metricId, MgServerCriticalValue mgServerCriticalValue) {

        MgServerCriticalValue updateServerMetric = mgServerCriticalValueRepository.findByMonGroupIdAndServerResourceIdAndMetricId(monitoringGroupId, serverResourceId, metricId);

        updateServerMetric.setWarning(mgServerCriticalValue.getWarning());
        updateServerMetric.setCritical(mgServerCriticalValue.getCritical());

        MgServerCriticalValue updateData = mgServerCriticalValueRepository.save(updateServerMetric);

        return updateData;
    }*/

}
