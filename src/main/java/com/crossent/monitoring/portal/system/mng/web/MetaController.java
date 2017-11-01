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

@RestController
public class MetaController extends BaseController{

    @Autowired
    MetaService metaService;

    @RequestMapping(value = "/system/management/meta", method = RequestMethod.GET)
    public PagingResVo pagingMeta(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<Meta> resPage = metaService.pagingMeta(paging, search);

        return resPage;
    }

/*    @Transactional
    @RequestMapping(value = "/system/management/meta", method = RequestMethod.POST)
    public void insertMeta(@RequestBody Meta meta){

        metaService.insertMeta(meta);
    }*/

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

    /*@Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.PUT)
    public void updateMeta(@PathVariable Integer metaId, @RequestBody Meta meta){

        metaService.updateMeta(metaId, meta);
    }*/

    @Transactional
    @RequestMapping(value = "/system/management/meta/{metaId}", method = RequestMethod.DELETE)
    public void deleteMeta(@PathVariable Integer metaId){

        metaService.deleteMeta(metaId);
    }
}
