package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.jpa.domain.Meta;
import com.crossent.monitoring.portal.jpa.domain.StateCode;
import com.crossent.monitoring.portal.system.mng.service.ManualService;
import com.crossent.monitoring.portal.system.mng.service.MetaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class MetaController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MetaController.class);

    @Autowired
    MetaService metaService;

    @ApiOperation(value = "시스템 관리 메타 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.GET)
    public PagingResVo pagingMeta(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        if(logger.isDebugEnabled()) {
            logger.debug("paging : {}", paging);
            logger.debug("search : {}", search);
        }
        PagingResVo<Meta> resPage = metaService.pagingMeta(paging, search);
        if(logger.isDebugEnabled()) {
            logger.debug("resPage : {}", resPage);
        }
        return resPage;
    }

    @ApiOperation(value = "시스템 관리 메타 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "meta", value = "메타", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.POST)
    public void insertMeta(@RequestBody Meta meta){

        if(logger.isDebugEnabled()) {
            logger.debug("meta : {}", meta);
        }
        metaService.insertMeta(meta);
    }

    @ApiOperation(value = "시스템 관리 메타 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaIds", value = "메타 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.DELETE)
    public void deleteMetas(@RequestParam Integer[] metaIds){

        if(logger.isDebugEnabled()) {
            logger.debug("metaIds : {}", Arrays.toString(metaIds));
        }
        metaService.deleteMetas(metaIds);
    }

    @ApiOperation(value = "시스템 관리 메타 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.GET)
    public Meta getMeta(@PathVariable Integer metaId){

        if(logger.isDebugEnabled()) {
            logger.debug("metaId : {}", metaId);
        }
        Meta getResource =  metaService.getMeta(metaId);
        if(logger.isDebugEnabled()) {
            logger.debug("getResource : {}", getResource);
        }
        return getResource;
    }

    @ApiOperation(value = "시스템 관리 메타 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "meta", value = "메타", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.PUT)
    public void updateMeta(@PathVariable Integer metaId, @RequestBody Meta meta){

        if(logger.isDebugEnabled()) {
            logger.debug("metaId : {}", metaId);
            logger.debug("meta : {}", meta);
        }
        metaService.updateMeta(metaId, meta);
    }

    @ApiOperation(value = "시스템 관리 메타 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.DELETE)
    public void deleteMeta(@PathVariable Integer metaId){

        if(logger.isDebugEnabled()) {
            logger.debug("metaId : {}", metaId);
        }
        metaService.deleteMeta(metaId);
    }

    @ApiOperation(value = "시스템 관리 메타 메뉴얼 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals", method = RequestMethod.GET)
    public Collection<Manual> getMetaManuals(@PathVariable Integer metaId) {

        if(logger.isDebugEnabled()) {
            logger.debug("metaId : {}", metaId);
        }
        Collection<Manual> manual = metaService.getMetaManuals(metaId);
        if(logger.isDebugEnabled()) {
            logger.debug("manual : {}", manual);
        }
        return manual;
    }

    @ApiOperation(value = "시스템 관리 메타 메뉴얼 다중추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "manualIds", value = "메뉴얼 ID 목록", required = true, dataType = "int", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals" , method = RequestMethod.POST)
    public void insertMetaManuals(@PathVariable Integer metaId, @RequestBody Integer[] manualIds){

        if(logger.isDebugEnabled()) {
            logger.debug("metaId : {}", metaId);
            logger.debug("manualIds : {}", Arrays.toString(manualIds));
        }
        metaService.insertMetaManuals(metaId, manualIds);
    }

    @ApiOperation(value = "시스템 관리 메타 메뉴얼 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "manualIds", value = "메뉴얼 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals" , method = RequestMethod.DELETE)
    public void deleteMetaManuals(@PathVariable Integer metaId, @RequestParam Integer[] manualIds) {

        if(logger.isDebugEnabled()) {
            logger.debug("metaId : {}", metaId);
            logger.debug("manualIds : {}", Arrays.toString(manualIds));
        }
        metaService.deleteMetaManuals(metaId, manualIds);
    }

    @ApiOperation(value = "시스템 관리 메타 메뉴얼 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "manualId", value = "메뉴얼 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals/{manualId}" , method = RequestMethod.DELETE)
    public void deleteMetaManual(@PathVariable Integer metaId, @PathVariable Integer manualId) {

        if(logger.isDebugEnabled()) {
            logger.debug("metaId : {}", metaId);
            logger.debug("manualId : {}", manualId);
        }
        metaService.deleteMetaManual(metaId, manualId);
    }

    /*@RequestMapping(value = "/system/management/meta/manuals", method = RequestMethod.GET)
    public Collection<Manual> getManuals(@RequestParam Integer metaId, @RequestParam String name) {

        Collection<Manual> manuals = metaService.getManuals(metaId, name);

        return manuals;
    }*/

    @ApiOperation(value = "시스템 관리 메타 상태코드 조회")
    @RequestMapping(value = "/system/management/meta/state-codes", method = RequestMethod.GET)
    public Collection<StateCode> getStateCodes() {

        Collection<StateCode> stateCodes = metaService.getStateCodes();
        if(logger.isDebugEnabled()) {
            logger.debug("stateCodes : {}", stateCodes);
        }
        return stateCodes;
    }
}
