package com.crossent.monitoring.portal.mongroup.monitoring.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.*;
import com.crossent.monitoring.portal.mongroup.monitoring.service.MonApplicationGroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class MonApplicationGroupController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MonApplicationGroupController.class);

    @Autowired
    MonApplicationGroupService monApplicationGroupService;



        //path, query, body, header

    @ApiOperation(value = "모니터링그룹의 어플리케이션그룹 상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/app-group/app-group-statuses", method = RequestMethod.GET)
    public PagingResVo<AppGroupStatusesResDto> pageAppGroupStatuses(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }

        PagingResVo<AppGroupStatusesResDto> appGroupStatuses = monApplicationGroupService.pageAppGroupStatuses(monitoringGroupId, paging, search);


        if(logger.isDebugEnabled()){
            logger.debug("appGroupStatuses : {}", appGroupStatuses);
        }

        return appGroupStatuses;
    }



    @ApiOperation(value = "모니터링그룹의 어플리케이션그룹에 속한 어플리케이션들의 상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/app-group/app-group-statuses/{appGroupId}/app-statuses", method = RequestMethod.GET)
    public PagingResVo<AppStatusesResDto> pageAppGroupServerStatuses(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @ModelAttribute("paging") PagingReqVo paging) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appGroupId : {}", appGroupId);
            logger.debug("paging : {}", paging);
        }

        PagingResVo<AppStatusesResDto> appGroupStatuses = monApplicationGroupService.pageAppGroupAppStatuses(monitoringGroupId, appGroupId, paging);

        if(logger.isDebugEnabled()){
            logger.debug("appGroupStatuses : {}", appGroupStatuses);
        }

        return appGroupStatuses;
    }


    @ApiOperation(value = "어플리케이션그룹의 이벤트 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/app-group/app-group-statuses/{appGroupId}/events", method = RequestMethod.GET)
    public PagingResVo<EventResDto> pageEvent(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @ModelAttribute("paging") PagingReqVo pagingReqVo, @ModelAttribute("search") SearchReqVo search){

        logger.debug("monitoringGroupId : {}", monitoringGroupId);
        logger.debug("appGroupId : {}", appGroupId);
        logger.debug("pagingReqVo : {}", pagingReqVo);
        logger.debug("search : {}", search);


        PagingResVo<EventResDto> resDtoPagingResVo = monApplicationGroupService.pageEvent(monitoringGroupId, appGroupId, pagingReqVo, search);
        logger.debug("resDtoPagingResVo : {}", resDtoPagingResVo);
        return resDtoPagingResVo;
    }

    @ApiOperation(value = "어플리케이션그룹의 로그 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "appGroupId", value = "어플리케이션 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/app-group/app-group-statuses/{appGroupId}/logs", method = RequestMethod.GET)
    public PagingResVo<LogResDto> pageLog(@PathVariable Integer monitoringGroupId, @PathVariable Integer appGroupId, @ModelAttribute("paging") PagingReqVo pagingReqVo, @ModelAttribute("search") SearchReqVo search){

        logger.debug("monitoringGroupId : {}", monitoringGroupId);
        logger.debug("appGroupId : {}", appGroupId);
        logger.debug("pagingReqVo : {}", pagingReqVo);
        logger.debug("search : {}", search);

        PagingResVo<LogResDto> resDtoPagingResVo = monApplicationGroupService.pageLog(monitoringGroupId, appGroupId, pagingReqVo, search);
        logger.debug("resDtoPagingResVo : {}", resDtoPagingResVo);
        return resDtoPagingResVo;
    }


}
