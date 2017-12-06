package com.crossent.monitoring.portal.mongroup.moniotring.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.EventResDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.LogResDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerDetailStatusDto;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerStatusesResDto;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonServerService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class MonServerController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MonServerController.class);

    @Autowired
    MonServerService serverService;

        //path, query, body, header

    @ApiOperation(value = "모니터링 그룹 서버상태 모니터링")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses", method = RequestMethod.GET)
    public PagingResVo<ServerStatusesResDto> pageServerStatuses(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }

        PagingResVo<ServerStatusesResDto> serverStatuses = serverService.pageServerStatuses(monitoringGroupId, paging, search);


        if(logger.isDebugEnabled()){
            logger.debug("serverStatuses : {}", serverStatuses);
        }

        return serverStatuses;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses/{serverResourceId}", method = RequestMethod.GET)
    public ServerDetailStatusDto getServerDetailStatus(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @ModelAttribute("search") SearchReqVo search) {

        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("serverResourceId : {}", serverResourceId);
            logger.debug("search : {}", search);
        }

        ServerDetailStatusDto serverDetailStatus = serverService.getServerDetailStatus(monitoringGroupId, serverResourceId, search);


//        if(logger.isDebugEnabled()){
//            logger.debug("serverStatuses : {}", serverStatuses);
//        }

        return serverDetailStatus;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses/{serverResourceId}/events", method = RequestMethod.GET)
    public PagingResVo<EventResDto> pageEvent(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @ModelAttribute("paging") PagingReqVo pagingReqVo, @ModelAttribute("search") SearchReqVo search){

        logger.debug("monitoringGroupId : {}", monitoringGroupId);
        logger.debug("serverResourceId : {}", serverResourceId);
        logger.debug("pagingReqVo : {}", pagingReqVo);
        logger.debug("search : {}", search);


        PagingResVo<EventResDto> resDtoPagingResVo = serverService.pageServerEvent(monitoringGroupId, serverResourceId, pagingReqVo, search);
        logger.debug("resDtoPagingResVo : {}", resDtoPagingResVo);
        return resDtoPagingResVo;
    }

    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses/{serverResourceId}/logs", method = RequestMethod.GET)
    public PagingResVo<LogResDto> pageLog(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @ModelAttribute("paging") PagingReqVo pagingReqVo, @ModelAttribute("search") SearchReqVo search){

        logger.debug("monitoringGroupId : {}", monitoringGroupId);
        logger.debug("serverResourceId : {}", serverResourceId);
        logger.debug("pagingReqVo : {}", pagingReqVo);
        logger.debug("search : {}", search);


        PagingResVo<LogResDto> resDtoPagingResVo = serverService.pageServerLog(serverResourceId, pagingReqVo, search);
        logger.debug("resDtoPagingResVo : {}", resDtoPagingResVo);
        return resDtoPagingResVo;
    }









}
