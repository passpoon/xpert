package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.system.mng.service.AppResourceService;
import com.crossent.monitoring.portal.system.mng.service.ServerResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;

@RestController
public class AppResourceController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AppResourceController.class);

    @Autowired
    AppResourceService appResourceService;

    @ApiOperation(value = "시스템 관리 어플리케이션 리소스 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/apps", method = RequestMethod.GET)
    public PagingResVo pagingAppResource(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        if(logger.isDebugEnabled()) {
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }
        PagingResVo<AppResource> resPage = appResourceService.pagingAppResource(paging, search);
        if(logger.isDebugEnabled()) {
            logger.debug("resPage : {}", resPage);
        }
        return resPage;
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 리소스 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appResource", value = "어플리케이션 리소스", required = false, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/apps", method = RequestMethod.POST)
    public void insertAppResource(@RequestBody AppResource appResource){

        if(logger.isDebugEnabled()) {
            logger.debug("appResource : {}", appResource);
        }
        appResourceService.insertAppResource(appResource);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 리소스 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appResourceIds", value = "어플리케이션 리소스 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/apps", method = RequestMethod.DELETE)
    public void deleteAppResources(@RequestParam Integer[] appResourceIds){

        if(logger.isDebugEnabled()) {
            logger.debug("appResourceIds : {}", Arrays.toString(appResourceIds));
        }
        appResourceService.deleteAppResources(appResourceIds);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 리소스 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/apps/{appResourceId}", method = RequestMethod.GET)
    public AppResource getAppResource(@PathVariable Integer appResourceId){

        if(logger.isDebugEnabled()) {
            logger.debug("appResourceId : {}", appResourceId);
        }
        AppResource getResource =  appResourceService.getAppResource(appResourceId);
        if(logger.isDebugEnabled()) {
            logger.debug("getResource : {}", getResource);
        }
        return getResource;
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 리소스 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "appResource", value = "어플리케이션 리소스", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/apps/{appResourceId}", method = RequestMethod.PUT)
    public void updateAppResource(@PathVariable Integer appResourceId, @RequestBody AppResource appResource){

        if(logger.isDebugEnabled()) {
            logger.debug("appResourceId : {}", appResourceId);
            logger.debug("appResource : {}", appResource);
        }
        appResourceService.updateAppResource(appResourceId, appResource);
    }

    @ApiOperation(value = "시스템 관리 어플리케이션 리소스 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appResourceId", value = "어플리케이션 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/apps/{appResourceId}", method = RequestMethod.DELETE)
    public void deleteAppResourceResource(@PathVariable Integer appResourceId){

        if(logger.isDebugEnabled()) {
            logger.debug("appResourceId : {}", appResourceId);
        }
        appResourceService.deleteAppResourceResource(appResourceId);
    }
}
