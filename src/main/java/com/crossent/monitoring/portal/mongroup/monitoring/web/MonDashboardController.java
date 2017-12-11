package com.crossent.monitoring.portal.mongroup.monitoring.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.*;
import com.crossent.monitoring.portal.mongroup.monitoring.service.MonDashboardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonDashboardController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(MonDashboardController.class);

    @Autowired
    MonDashboardService monDashboardService;

    @ApiOperation(value = "대시보드 서버 통계정보 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/server-statistics", method = RequestMethod.GET)
    public StatisticsResDto getServerStatistics(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        StatisticsResDto serverStatistics = monDashboardService.getServerStatistics(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("serverStatistics : {}", serverStatistics);
        }

        return serverStatistics;
    }


    @ApiOperation(value = "대시보드 서버상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/server-statuses", method = RequestMethod.GET)
    public PagingResVo<ServerStatusesResDto> listServerStatuses(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        PagingResVo<ServerStatusesResDto> serverStatuses = monDashboardService.listServerStatuses(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("serverStatuses : {}", serverStatuses);
        }

        return serverStatuses;
    }


    @ApiOperation(value = "대시보드 서버그룹 상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/server-group-statuses", method = RequestMethod.GET)
    public PagingResVo<ServerGroupStatusesResDto> listServerGroupStatuses(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        PagingResVo<ServerGroupStatusesResDto> serverGroupStatusesResDtos = monDashboardService.listServerGroupStatuses(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("serverGroupStatusesResDtos : {}", serverGroupStatusesResDtos);
        }

        return serverGroupStatusesResDtos;
    }


    @ApiOperation(value = "대시보드 어플리케이션 통계정보 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/apps-statistics", method = RequestMethod.GET)
    public StatisticsResDto getAppStatistics(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        StatisticsResDto appStatistics = monDashboardService.getAppStatistics(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("appStatistics : {}", appStatistics);
        }

        return appStatistics;
    }


    @ApiOperation(value = "대시보드 어플리케이션 상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/app-statuses", method = RequestMethod.GET)
    public PagingResVo<AppStatusesResDto> listAppStatuses(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        PagingResVo<AppStatusesResDto> appStatusesResDtos = monDashboardService.listAppStatuses(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("appStatusesResDtos : {}", appStatusesResDtos);
        }

        return appStatusesResDtos;
    }


    @ApiOperation(value = "대시보드 어플리케이션그룹 상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/app-group-statuses", method = RequestMethod.GET)
    public PagingResVo<AppGroupStatusesResDto> listAppGroupStatuses(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        PagingResVo<AppGroupStatusesResDto> appGroupStatusesResDtos = monDashboardService.listAppGroupStatuses(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("appGroupStatusesResDtos : {}", appGroupStatusesResDtos);
        }

        return appGroupStatusesResDtos;
    }


    @ApiOperation(value = "대시보드 이벤트 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/events", method = RequestMethod.GET)
    public PagingResVo<EventResDto> listEvent(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        PagingResVo<EventResDto> eventResDtos = monDashboardService.listEvent(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("eventResDtos : {}", eventResDtos);
        }

        return eventResDtos;
    }


    @ApiOperation(value = "대시보드 알람 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/dashboard/alarms", method = RequestMethod.GET)
    public PagingResVo<AlarmResDto> listAlarm(@PathVariable Integer monitoringGroupId) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
        }

        PagingResVo<AlarmResDto> alarmResDtos = monDashboardService.listAlarm(monitoringGroupId);


        if(logger.isDebugEnabled()){
            logger.debug("alarmResDtos : {}", alarmResDtos);
        }

        return alarmResDtos;
    }




}
