package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.system.mng.service.ServerResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class ServerResourceController extends BaseController{

    @Autowired
    ServerResourceService serverResourceService;

    @ApiOperation(value = "시스템 관리 서버 리소스 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/servers", method = RequestMethod.GET)
    public PagingResVo pagingServerResource(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<ServerResource> resPage = serverResourceService.pagingServerResource(paging, search);

        return resPage;
    }

    @ApiOperation(value = "시스템 관리 서버 리소스 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverResource", value = "서버 리소스", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/servers", method = RequestMethod.POST)
    public void insertServerResource(@RequestBody ServerResource serverResource){

        serverResourceService.insertServerResource(serverResource);
    }

    @ApiOperation(value = "시스템 관리 서버 리소스 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverResourceIds", value = "서버 리소스 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/servers", method = RequestMethod.DELETE)
    public void deleteServerResources(@RequestParam Integer[] serverResourceIds){

        serverResourceService.deleteServerResources(serverResourceIds);
    }

    @ApiOperation(value = "시스템 관리 서버 리소스 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/servers/{serverResourceId}", method = RequestMethod.GET)
    public ServerResource getServerResource(@PathVariable Integer serverResourceId){

        ServerResource getResource =  serverResourceService.getServerResource(serverResourceId);

        return getResource;
    }

    @ApiOperation(value = "시스템 관리 서버 리소스 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "serverResource", value = "서버 리소스", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/servers/{serverResourceId}", method = RequestMethod.PUT)
    public void updateServerResource(@PathVariable Integer serverResourceId, @RequestBody ServerResource serverResource){

        serverResourceService.updateServerResource(serverResourceId, serverResource);
    }

    @ApiOperation(value = "시스템 관리 서버 리소스 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverResourceId", value = "서버 리소스 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/servers/{serverResourceId}", method = RequestMethod.DELETE)
    public void deleteServerResource(@PathVariable Integer serverResourceId){

        serverResourceService.deleteServerResource(serverResourceId);
    }
}
