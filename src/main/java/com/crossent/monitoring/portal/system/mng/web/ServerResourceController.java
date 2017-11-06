package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.system.mng.service.ServerResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ServerResourceController extends BaseController{

    @Autowired
    ServerResourceService serverResourceService;

    @RequestMapping(value = "/system/management/servers", method = RequestMethod.GET)
    public PagingResVo pagingServerResource(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<ServerResource> resPage = serverResourceService.pagingServerResource(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/servers", method = RequestMethod.POST)
    public void insertServerResource(@RequestBody ServerResource serverResource){

        serverResourceService.insertServerResource(serverResource);
    }

    @Transactional
    @RequestMapping(value = "/system/management/servers", method = RequestMethod.DELETE)
    public void deleteServerResources(@RequestParam Integer[] serverResourceIds){

        serverResourceService.deleteServerResources(serverResourceIds);
    }

    @RequestMapping(value = "/system/management/servers/{serverResourceId}", method = RequestMethod.GET)
    public ServerResource getServerResource(@PathVariable Integer serverResourceId){

        ServerResource getResource =  serverResourceService.getServerResource(serverResourceId);

        return getResource;
    }

    @Transactional
    @RequestMapping(value = "/system/management/servers/{serverResourceId}", method = RequestMethod.PUT)
    public void updateServerResource(@PathVariable Integer serverResourceId, @RequestBody ServerResource serverResource){

        serverResourceService.updateServerResource(serverResourceId, serverResource);
    }

    @Transactional
    @RequestMapping(value = "/system/management/servers/{serverResourceId}", method = RequestMethod.DELETE)
    public void deleteServerResource(@PathVariable Integer serverResourceId){

        serverResourceService.deleteServerResource(serverResourceId);
    }
}
