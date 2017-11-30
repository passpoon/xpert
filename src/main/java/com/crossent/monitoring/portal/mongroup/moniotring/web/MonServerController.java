package com.crossent.monitoring.portal.mongroup.moniotring.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.ServerStatusesResDto;
import com.crossent.monitoring.portal.mongroup.moniotring.service.MonServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.Collection;
import java.util.List;

@RestController
public class MonServerController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MonServerController.class);

    @Autowired
    MonServerService serverService;


    @Transactional
    @RequestMapping(value = "/monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses", method = RequestMethod.GET)
    public PagingResVo<ServerStatusesResDto> getServerStatuses(@PathVariable Integer monitoringGroupId, @ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {


        if(logger.isDebugEnabled()){
            logger.debug("monitoringGroupId : {}", monitoringGroupId);
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }

        PagingResVo<ServerStatusesResDto> serverStatuses = serverService.getServerStatuses(monitoringGroupId, paging, search);


        if(logger.isDebugEnabled()){
            logger.debug("serverStatuses : {}", serverStatuses);
        }

        return serverStatuses;
    }







}
