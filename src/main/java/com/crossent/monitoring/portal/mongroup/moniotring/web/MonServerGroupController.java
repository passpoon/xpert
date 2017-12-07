package com.crossent.monitoring.portal.mongroup.moniotring.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.*;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonServerGroupService;
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
public class MonServerGroupController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MonServerGroupController.class);

    @Autowired
    MonServerGroupService monServerGroupService;

        //path, query, body, header

    @ApiOperation(value = "모니터링그룹의 서버그룹 상태 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server-group/server-statuses", method = RequestMethod.GET)
    public PagingResVo<ServerGroupStatusesResDto> pageServerStatuses(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }

        PagingResVo<ServerGroupStatusesResDto> serverGroupStatuses = monServerGroupService.pageServerGroupStatuses(monitoringGroupId, paging, search);


        if(logger.isDebugEnabled()){
            logger.debug("serverGroupStatuses : {}", serverGroupStatuses);
        }

        return serverGroupStatuses;
    }


}
