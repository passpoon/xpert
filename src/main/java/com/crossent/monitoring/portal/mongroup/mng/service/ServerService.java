package com.crossent.monitoring.portal.mongroup.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import com.crossent.monitoring.portal.jpa.repository.MgServerCriticalValueRepository;
import com.crossent.monitoring.portal.jpa.repository.MgServerRepository;
import com.crossent.monitoring.portal.mongroup.mng.dto.MgServerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServerService {
    private static Logger logger = LoggerFactory.getLogger(ServerService.class);

    @Autowired
    MgServerRepository mgServerRepository;

    @Autowired
    MgServerCriticalValueRepository mgServerCriticalValueRepository;

    public PagingResVo<MgServerDto> pagingServer(Integer monitoringGroupId, PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Page<MgServer> allMonGroupId = mgServerRepository.findAllByMonGroupId(pagingReqVo.toPagingRequest(), monitoringGroupId); // Dto 새로 생성, repository에 monGroupId로 조회

        PagingResVo<MgServerDto> resPage = new PagingResVo<MgServerDto>(allMonGroupId, false);

        List<MgServer> content = allMonGroupId.getContent();
        List<MgServerDto> mgServerDtos = new ArrayList<MgServerDto>();
        for(MgServer mgServer : content){
            MgServerDto mgServerDto = new MgServerDto();
            mgServerDto.setMonGroupId(mgServer.getMonGroupId());
            mgServerDto.setServerResourceId(mgServer.getServerResourceId());
            mgServerDto.setServerName(mgServer.getServerResource().getName());
            mgServerDto.setHostName(mgServer.getServerResource().getHostName());
            mgServerDto.setMonitoringYn(mgServer.getMonitoringYn());
            mgServerDto.setDashboardYn(mgServer.getDashboardYn());

            mgServerDtos.add(mgServerDto);
        }
        resPage.setList(mgServerDtos);

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
        Page<MgServer> mgServers = null;
        if (key == null) {
            //TODO 전체조회
            mgServers = mgServerRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "serverResourceName": {
                    mgServers = mgServerRepository.findAllByMonGroupIdAndServerResource_NameLike(pagingReqVo.toPagingRequest(), monitoringGroupId,  keyword);
                }
                break;
                case "hostName": {
                    mgServers = mgServerRepository.findAllByMonGroupIdAndServerResource_HostNameLike(pagingReqVo.toPagingRequest(), monitoringGroupId,  keyword);
                }
                break;
            }
        }

        return resPage;
    }

    public MgServer updateServer(Integer monitoringGroupId, Integer serverResourceId, MgServer mgServer) {

        MgServer updateServer = mgServerRepository.findByMonGroupIdAndServerResourceId(monitoringGroupId, serverResourceId);

        updateServer.setMonitoringYn(mgServer.getMonitoringYn());
        updateServer.setDashboardYn(mgServer.getDashboardYn());

        MgServer updateData = mgServerRepository.save(updateServer);

        return updateData;
    }

    public Collection<MgServerCriticalValue> getServerMetrics(Integer monitoringGroupId, Integer serverResourceId) {

        /*MgServerCriticalValue id = mgServerCriticalValueRepository.findByMonGroupIdAndServerResourceId(monitoringGroupId, serverResourceId);

        MgServerCriticalValue out = new MgServerCriticalValue();
        out.setMetricId(id.getMetricId());
        out.setWarning(id.getWarning());
        out.setCritical(id.getCritical());*/

        MgServer mgServer = mgServerRepository.findByMonGroupIdAndServerResourceId(monitoringGroupId, serverResourceId);
        Collection<MgServerCriticalValue> mgServerCriticalValues = mgServer.getMgServerCriticalValues();

        return mgServerCriticalValues;
    }

    public MgServerCriticalValue updateServerMetrics(Integer monitoringGroupId, Integer serverResourceId, Integer metricId, MgServerCriticalValue mgServerCriticalValue) {

        MgServerCriticalValue updateServerMetric = mgServerCriticalValueRepository.findByMonGroupIdAndServerResourceIdAndMetricId(monitoringGroupId, serverResourceId, metricId);

        updateServerMetric.setWarning(mgServerCriticalValue.getWarning());
        updateServerMetric.setCritical(mgServerCriticalValue.getCritical());

        MgServerCriticalValue updateData = mgServerCriticalValueRepository.save(updateServerMetric);

        return updateData;
    }

}
