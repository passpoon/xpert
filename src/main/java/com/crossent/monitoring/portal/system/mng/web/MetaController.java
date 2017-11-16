package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.jpa.domain.Meta;
import com.crossent.monitoring.portal.system.mng.service.ManualService;
import com.crossent.monitoring.portal.system.mng.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class MetaController extends BaseController{

    @Autowired
    MetaService metaService;

    @RequestMapping(value = "/system/management/meta", method = RequestMethod.GET)
    public PagingResVo pagingMeta(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<Meta> resPage = metaService.pagingMeta(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.POST)
    public void insertMeta(@RequestBody Meta meta){

        metaService.insertMeta(meta);
    }

    @Transactional
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.DELETE)
    public void deleteMetas(@RequestParam Integer[] metaIds){

        metaService.deleteMetas(metaIds);
    }

    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.GET)
    public Meta getMeta(@PathVariable Integer metaId){

        Meta getResource =  metaService.getMeta(metaId);

        return getResource;
    }

    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.PUT)
    public void updateMeta(@PathVariable Integer metaId, @RequestBody Meta meta){

        metaService.updateMeta(metaId, meta);
    }

    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.DELETE)
    public void deleteMeta(@PathVariable Integer metaId){

        metaService.deleteMeta(metaId);
    }

    @RequestMapping(value = "/system/management/meta/{metaId}/manuals", method = RequestMethod.GET)
    public Collection<Manual> getMetaManuals(@PathVariable Integer metaId) {

        Collection<Manual> manual = metaService.getMetaManuals(metaId);

        return manual;
    }

    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals" , method = RequestMethod.POST)
    public void insertMetaManuals(@PathVariable Integer metaId, @RequestBody Integer[] manualIds){

        metaService.insertMetaManuals(metaId, manualIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals" , method = RequestMethod.DELETE)
    public void deleteMetaManuals(@PathVariable Integer metaId, @RequestParam Integer[] manualIds) {

        metaService.deleteMetaManuals(metaId, manualIds);
    }

    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}/manuals/{manualId}" , method = RequestMethod.DELETE)
    public void deleteMetaManual(@PathVariable Integer metaId, @PathVariable Integer manualId) {

        metaService.deleteMetaManual(metaId, manualId);
    }

    @RequestMapping(value = "/system/management/meta/manuals", method = RequestMethod.GET)
    public Collection<Manual> getManuals(@RequestParam Integer metaId, @RequestParam String name) {

        Collection<Manual> manuals = metaService.getManuals(metaId, name);

        return manuals;
    }
}
