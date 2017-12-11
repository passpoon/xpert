package com.crossent.monitoring.portal.mongroup.monitoring.web;

import com.crossent.monitoring.portal.common.constants.ResourceType;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.EventResDto;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.LogResDto;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.ServerDetailStatusDto;
import com.crossent.monitoring.portal.mongroup.monitoring.dto.ServerStatusesResDto;
import com.crossent.monitoring.portal.mongroup.monitoring.service.MonCommonService;
import com.crossent.monitoring.portal.mongroup.monitoring.service.MonServerService;
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
public class MonEventController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MonEventController.class);


    @Autowired
    MonCommonService monCommonService;



    @ApiOperation(value = "모니터링 그룹 이벤트 조회")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "monitoringGroupId", value = "모니터링 그룹 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
                               @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
                               @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @Transactional
   @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/event/events", method = RequestMethod.GET)
    public PagingResVo<EventResDto> pageEvent(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo pagingReqVo, @ModelAttribute("search") SearchReqVo search){

        logger.debug("monitoringGroupId : {}", monitoringGroupId);
        logger.debug("pagingReqVo : {}", pagingReqVo);
        logger.debug("search : {}", search);

        List<Integer> ids = null;
        PagingResVo<EventResDto> resDtoPagingResVo = monCommonService.pageEvent(monitoringGroupId, null, ids, pagingReqVo, search);
        logger.debug("resDtoPagingResVo : {}", resDtoPagingResVo);
        return resDtoPagingResVo;

    }


}
