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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class MetaController extends BaseController{

    @Autowired
    MetaService metaService;

    @ApiOperation(value = "시스템 관리 메타 페이징")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paging", value = "페이징 정보", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "검색 정보", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.GET)
    public PagingResVo pagingMeta(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<Meta> resPage = metaService.pagingMeta(paging, search);

        return resPage;
    }

    @ApiOperation(value = "시스템 관리 메타 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "meta", value = "메타", required = true, dataType = "string", paramType = "body"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.POST)
    public void insertMeta(@RequestBody Meta meta){

        metaService.insertMeta(meta);
    }

    @ApiOperation(value = "시스템 관리 메타 선택삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaIds", value = "메타 ID 목록", required = true, dataType = "int", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.DELETE)
    public void deleteMetas(@RequestParam Integer[] metaIds){

        metaService.deleteMetas(metaIds);
    }

    @ApiOperation(value = "시스템 관리 메타 상세조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.GET)
    public Meta getMeta(@PathVariable Integer metaId){

        Meta getResource =  metaService.getMeta(metaId);

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

        metaService.updateMeta(metaId, meta);
    }

    @ApiOperation(value = "시스템 관리 메타 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.DELETE)
    public void deleteMeta(@PathVariable Integer metaId){

        metaService.deleteMeta(metaId);
    }

    @ApiOperation(value = "시스템 관리 메타 메뉴얼 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals", method = RequestMethod.GET)
    public Collection<Manual> getMetaManuals(@PathVariable Integer metaId) {

        Collection<Manual> manual = metaService.getMetaManuals(metaId);

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

        metaService.insertMetaManuals(metaId, manualIds);
    }

    @ApiOperation(value = "시스템 관리 메타 메뉴얼 다중추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "manualIds", value = "메뉴얼 ID 목록", required = true, dataType = "string", paramType = "query"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals" , method = RequestMethod.DELETE)
    public void deleteMetaManuals(@PathVariable Integer metaId, @RequestParam Integer[] manualIds) {

        metaService.deleteMetaManuals(metaId, manualIds);
    }

    @ApiOperation(value = "시스템 관리 메타 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "metaId", value = "메타 ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "manualId", value = "메뉴얼 ID", required = true, dataType = "int", paramType = "path"),
    })
    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals/{manualId}" , method = RequestMethod.DELETE)
    public void deleteMetaManual(@PathVariable Integer metaId, @PathVariable Integer manualId) {

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

        return stateCodes;
    }
}
