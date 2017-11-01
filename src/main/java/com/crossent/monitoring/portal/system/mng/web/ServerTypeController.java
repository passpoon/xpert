package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.system.mng.service.ServerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

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
    public void deleteServerType(@PathVariable Integer serverTypeId) {

        serverTypeService.deleteServerType(serverTypeId);
    }

    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/mesurements", method = RequestMethod.GET)
    public Collection<Measurement> getServerTypeMeasurement(@PathVariable Integer serverTypeId) {

        Collection<Measurement> measurements = serverTypeService.getServerTypeMeasurement(serverTypeId);
        return measurements;
    }

    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/mesurements" , method = RequestMethod.POST)
    public void insertServerTypeMeasurement(@PathVariable Integer serverTypeId, @RequestBody Measurement measurement){

        serverTypeService.insertServerTypeMeasurement(serverTypeId, measurement);
    }

    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/mesurements" , method = RequestMethod.DELETE)
    public void deleteServerTypeMeasurements(@PathVariable Integer serverTypeId, @RequestParam Integer[] measurementIds) {

        serverTypeService.deleteServerTypeMeasurements(serverTypeId, measurementIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/server-types/{serverTypeId}/mesurements/{measurementId}" , method = RequestMethod.DELETE)
    public void deleteServerTypeMeasurement(@PathVariable Integer serverTypeId, @PathVariable Integer measurementId) {

        serverTypeService.deleteServerTypeMeasurement(serverTypeId, measurementId);
    }

}