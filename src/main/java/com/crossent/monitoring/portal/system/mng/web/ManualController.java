package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.system.mng.service.ManualService;
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
public class ManualController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(ManualController.class);

    @Autowired
    ManualService manualService;

    @ApiOperation(value = "시스템 관리 메뉴얼 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/manuals", method = RequestMethod.GET)
    public PagingResVo pagingManual(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        if(logger.isDebugEnabled()) {
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }
        PagingResVo<Manual> resPage = manualService.pagingManual(paging, search);
        if(logger.isDebugEnabled()) {
            logger.debug("resPage : {}", resPage);
        }
        return resPage;
    }

    @ApiOperation(value = "시스템 관리 메뉴얼 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "manual", value = "메뉴얼", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/manuals", method = RequestMethod.POST)
    public void insertManual(@RequestBody Manual manual){

        if(logger.isDebugEnabled()) {
            logger.debug("manual : {}", manual);
        }
        manualService.insertManual(manual);
    }

    @ApiOperation(value = "시스템 관리 메뉴얼 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "manualIds", value = "메뉴얼 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/manuals", method = RequestMethod.DELETE)
    public void deleteManuals(@RequestParam Integer[] manualIds){

        if(logger.isDebugEnabled()) {
            logger.debug("manualIds : {}", Arrays.toString(manualIds));
        }
        manualService.deleteManuals(manualIds);
    }

    @ApiOperation(value = "시스템 관리 메뉴얼 정보 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "manualId", value = "메뉴얼 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/manuals/{manualId}", method = RequestMethod.GET)
    public Manual getManual(@PathVariable Integer manualId){

        if(logger.isDebugEnabled()) {
            logger.debug("manualId : {}", manualId);
        }
        Manual getResource =  manualService.getManual(manualId);
        if(logger.isDebugEnabled()) {
            logger.debug("getResource : {}", getResource);
        }
        return getResource;
    }

    @ApiOperation(value = "시스템 관리 메뉴얼 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "manualId", value = "메뉴얼 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "manual", value = "메뉴얼", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/manuals/{manualId}", method = RequestMethod.PUT)
    public void updateManual(@PathVariable Integer manualId, @RequestBody Manual manual){

        if(logger.isDebugEnabled()) {
            logger.debug("manualId : {}", manualId);
            logger.debug("manual : {}", manual);
        }
        manualService.updateManual(manualId, manual);
    }

    @ApiOperation(value = "시스템 관리 메뉴얼 정보 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "manualId", value = "메뉴얼 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/manuals/{manualId}", method = RequestMethod.DELETE)
    public void deleteManual(@PathVariable Integer manualId){

        if(logger.isDebugEnabled()) {
            logger.debug("manualId : {}", manualId);
        }
        manualService.deleteManual(manualId);
    }
}
