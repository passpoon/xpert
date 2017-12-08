package com.crossent.monitoring.portal.mongroup.moniotring.web;

import com.crossent.monitoring.portal.common.constants.ResourceType;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.*;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonApplicationService;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonCommonService;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonServerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class MonApplicationController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MonApplicationController.class);

    @Autowired
    MonApplicationService monApplicationService;

    @Autowired
    MonCommonService monCommonService;

        //path, query, body, header

    @ApiOperation(value = "모니터링그룹의 어플리케이션 상태 상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/app/app-statuses", method = RequestMethod.GET)
    public PagingResVo<AppStatusesResDto> pageAppStatuses(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }

        PagingResVo<AppStatusesResDto> appStatuses = monApplicationService.pageAppStatuses(monitoringGroupId, paging, search);


        if(logger.isDebugEnabled()){
            logger.debug("appStatuses : {}", appStatuses);
        }

        return appStatuses;
    }

    @ApiOperation(value = "어플리케이션 상세 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/app/app-statuses/{appResourceId}", method = RequestMethod.GET)
    public AppDetailStatusDto getAppDetailStatus(@PathVariable Integer monitoringGroupId, @PathVariable Integer appResourceId, @ModelAttribute("search") SearchReqVo search) {

        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("appResourceId : {}", appResourceId);
            logger.debug("search : {}", search);
        }

        AppDetailStatusDto appDetailStatus = monCommonService.getAppDetailStatus(appResourceId, search);


        return appDetailStatus;
    }

//    @ApiOperation(value = "서버 이벤트 조회")
//    @ApiImplicitParams({
//                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
//                               @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
//                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
//                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
//    })
//    @Transactional
//    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses/{serverResourceId}/events", method = RequestMethod.GET)
//    public PagingResVo<EventResDto> pageEvent(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @ModelAttribute("paging") PagingReqVo pagingReqVo, @ModelAttribute("search") SearchReqVo search){
//
//        logger.debug("monitoringGroupId : {}", monitoringGroupId);
//        logger.debug("serverResourceId : {}", serverResourceId);
//        logger.debug("pagingReqVo : {}", pagingReqVo);
//        logger.debug("search : {}", search);
//
//
//        PagingResVo<EventResDto> resDtoPagingResVo = monCommonService.pageEvent(monitoringGroupId, ResourceType.SERVER, serverResourceId, pagingReqVo, search);
//        logger.debug("resDtoPagingResVo : {}", resDtoPagingResVo);
//        return resDtoPagingResVo;
//    }
//
//    @ApiOperation(value = "서버 로그 조회")
//    @ApiImplicitParams({
//                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
//                               @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
//                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
//                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
//    })
//    @Transactional
//    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses/{serverResourceId}/logs", method = RequestMethod.GET)
//    public PagingResVo<LogResDto> pageLog(@PathVariable Integer monitoringGroupId, @PathVariable Integer serverResourceId, @ModelAttribute("paging") PagingReqVo pagingReqVo, @ModelAttribute("search") SearchReqVo search){
//
//        logger.debug("monitoringGroupId : {}", monitoringGroupId);
//        logger.debug("serverResourceId : {}", serverResourceId);
//        logger.debug("pagingReqVo : {}", pagingReqVo);
//        logger.debug("search : {}", search);
//
//        PagingResVo<LogResDto> resDtoPagingResVo = monCommonService.pageServerLog(serverResourceId, pagingReqVo, search);
//        logger.debug("resDtoPagingResVo : {}", resDtoPagingResVo);
//        return resDtoPagingResVo;
//    }
}
