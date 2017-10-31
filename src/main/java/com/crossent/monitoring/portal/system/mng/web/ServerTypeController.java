package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.system.mng.service.ServerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class ServerTypeController extends BaseController{

    @Autowired
    ServerTypeService serverTypeService;

    @RequestMapping(value = "/system/management/server-types", method = RequestMethod.GET)
    public PagingResVo pagingServerType(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search) {

        PagingResVo<ServerType> resPage = serverTypeService.pagingServerType(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/server-types", method = RequestMethod.POST)
    public void insertServerType(@RequestBody ServerType serverType) {

        serverTypeService.insertServerType(serverType);
    }

    @Transactional
    @RequestMapping(value = "/system/management/server-types", method = RequestMethod.DELETE)
    public void deleteServerTypes(@RequestParam Integer[] serverTypeIds){

        serverTypeService.deleteServerTypes(serverTypeIds);
    }

    @RequestMapping(value = "/system/management/server-types/{serverTypeId}", method = RequestMethod.GET)
    public ServerType getServerType(@PathVariable Integer serverTypeId) {

        ServerType getUser =  serverTypeService.getServerType(serverTypeId);

        return getUser;
    }

    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}", method = RequestMethod.PUT)
    public void updateServerType(@PathVariable Integer serverTypeId, @RequestBody ServerType serverType) {

        serverTypeService.updateServerType(serverTypeId, serverType);
    }

    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}", method = RequestMethod.DELETE)
    public void deleteUserGroup(@PathVariable Integer serverTypeId){

        serverTypeService.deleteServerType(serverTypeId);
    }





}
