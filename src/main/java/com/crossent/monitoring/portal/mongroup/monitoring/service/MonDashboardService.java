package com.crossent.monitoring.portal.mongroup.monitoring.service;

import com.crossent.monitoring.portal.common.constants.ResourceType;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MonDashboardService {
    private Logger logger = LoggerFactory.getLogger(MonDashboardService.class);

    @Autowired
    MonServerService monServerService;

    @Autowired
    MonServerGroupService monServerGroupService;

    @Autowired
    MonApplicationService monApplicationService;

    @Autowired
    MonApplicationGroupService monApplicationGroupService;

    @Autowired
    MonCommonService monCommonService;




//서버 통계 정보

    public StatisticsResDto getServerStatistics(Integer monGroupId){
        StatisticsResDto statisticsResDto = new StatisticsResDto();
        statisticsResDto.setAllCnt(0);
        statisticsResDto.setRunCnt(0);
        statisticsResDto.setStopCnt(0);
        statisticsResDto.setErrorCnt(0);
        return statisticsResDto;
    }

//서버 리스트
    public PagingResVo<ServerStatusesResDto> listServerStatuses(Integer monGroupId) {
        PagingReqVo pagingReqVo = new PagingReqVo(0, ApplicationProperties.dashboardListMaxCntServer);
        SearchReqVo searchReqVo = new SearchReqVo();
        PagingResVo<ServerStatusesResDto> pageServerStatuses = monServerService.pageServerStatuses(monGroupId, pagingReqVo, searchReqVo, true);
        return pageServerStatuses;
    }


//서버 그룹 리스트
    public PagingResVo<ServerGroupStatusesResDto> listServerGroupStatuses(Integer monGroupId){
        PagingReqVo pagingReqVo = new PagingReqVo(0, ApplicationProperties.dashboardListMaxCntServerGroup);
        SearchReqVo searchReqVo = new SearchReqVo();
        PagingResVo<ServerGroupStatusesResDto> serverGroupStatuses = monServerGroupService.pageServerGroupStatuses(monGroupId, pagingReqVo, searchReqVo, true);
        return serverGroupStatuses;
    }
//어플리케이션 통계 정보

    public StatisticsResDto getAppStatistics(Integer monGroupId){
        StatisticsResDto statisticsResDto = new StatisticsResDto();
        statisticsResDto.setAllCnt(0);
        statisticsResDto.setRunCnt(0);
        statisticsResDto.setStopCnt(0);
        statisticsResDto.setErrorCnt(0);
        return statisticsResDto;
    }

//어플리케이션 리스트
    public PagingResVo<AppStatusesResDto> listAppStatuses(Integer monGroupId){
        PagingReqVo pagingReqVo = new PagingReqVo(0, ApplicationProperties.dashboardListMaxCntApp);
        SearchReqVo searchReqVo = new SearchReqVo();
        PagingResVo<AppStatusesResDto> pageAppStatuses = monApplicationService.pageAppStatuses(monGroupId, pagingReqVo, searchReqVo, true);
        return pageAppStatuses;
    }


//어플리케이션 그룹 리스트
    public PagingResVo<AppGroupStatusesResDto> listAppGroupStatuses(Integer monGroupId){
        PagingReqVo pagingReqVo = new PagingReqVo(0, ApplicationProperties.dashboardListMaxCntAppGroup);
        SearchReqVo searchReqVo = new SearchReqVo();
        PagingResVo<AppGroupStatusesResDto> pageAppGroupStatuses = monApplicationGroupService.pageAppGroupStatuses(monGroupId, pagingReqVo, searchReqVo, true);

        return pageAppGroupStatuses;
    }


//이벤트 리스트

    public PagingResVo<EventResDto> listEvent(Integer monGroupId){
        PagingReqVo pagingReqVo = new PagingReqVo(0, ApplicationProperties.dashboardListMaxCntEvent);
        SearchReqVo searchReqVo = new SearchReqVo();
        ResourceType resourceType = null;
        List<Integer> resourceIds = null;
        PagingResVo<EventResDto> pageEvent = monCommonService.pageEvent(monGroupId, resourceType, resourceIds, pagingReqVo, searchReqVo);
        return pageEvent;
    }


    public PagingResVo<AlarmResDto> listAlarm(Integer monGroupId){

        //PagingReqVo pagingReqVo = new PagingReqVo(0, ApplicationProperties.dashboardListMaxCntAlarm);

        PagingReqVo pagingReqVo = new PagingReqVo();

        List<AlarmResDto> alarms = new ArrayList<AlarmResDto>();
        AlarmResDto dto = new AlarmResDto();
        dto.setAlarmId(10L);
        dto.setChannel("Email");
        dto.setCompleateYn("N");
        dto.setContent("Shutdown Mysql Application");
        dto.setEventHistoryId(200L);
        dto.setMonGroupId(1);
        dto.setRegistDttm("2017-12-11 23:11:10");
        dto.setUpdateDttm("2017-12-11 23:11:10");
        dto.setState("처리완료");


        AlarmResDto dto2 = new AlarmResDto();
        dto2.setAlarmId(10L);
        dto2.setChannel("Chat");
        dto2.setCompleateYn("N");
        dto2.setContent("테스트");
        dto2.setEventHistoryId(200L);
        dto2.setMonGroupId(1);
        dto2.setRegistDttm("2017-12-10 09:11:10");
        dto2.setUpdateDttm("2017-12-10 09:11:10");
        dto2.setState("처리중");


        alarms.add(dto);
        alarms.add(dto2);

        PagingResVo<AlarmResDto> alarmResVos = new PagingResVo<AlarmResDto>();
        alarmResVos.setList(alarms);
        return alarmResVos;
    }









}
