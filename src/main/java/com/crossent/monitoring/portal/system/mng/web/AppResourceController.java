package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.system.mng.service.AppResourceService;
import com.crossent.monitoring.portal.system.mng.service.ServerResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class AppResourceController extends BaseController{

    @Autowired
    AppResourceService appResourceService;

    @RequestMapping(value = "/system/management/apps", method = RequestMethod.GET)
    public PagingResVo pagingAppResource(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<AppResource> resPage = appResourceService.pagingAppResource(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/apps", method = RequestMethod.POST)
    public void insertAppResource(@RequestBody AppResource appResource){

        appResourceService.insertAppResource(appResource);
    }


    @Transactional
    @RequestMapping(value = "/system/management/apps", method = RequestMethod.DELETE)
    public void deleteAppResources(@RequestParam Integer[] appResourceIds){

        appResourceService.deleteAppResources(appResourceIds);
    }

    @RequestMapping(value = "/system/management/apps/{appResourceId}", method = RequestMethod.GET)
    public AppResource getAppResource(@PathVariable Integer appResourceId){

        AppResource getResource =  appResourceService.getAppResource(appResourceId);

        return getResource;
    }

    @Transactional
    @RequestMapping(value = "/system/management/apps/{appResourceId}", method = RequestMethod.PUT)
    public void updateAppResource(@PathVariable Integer appResourceId, @RequestBody AppResource appResource){

        appResourceService.updateAppResource(appResourceId, appResource);
    }

    @Transactional
    @RequestMapping(value = "/system/management/apps/{appResourceId}", method = RequestMethod.DELETE)
    public void deleteAppResourceResource(@PathVariable Integer appResourceId){

        appResourceService.deleteAppResourceResource(appResourceId);
    }
}
