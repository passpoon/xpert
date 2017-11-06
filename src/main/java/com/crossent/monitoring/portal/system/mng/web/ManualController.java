package com.crossent.monitoring.portal.system.mng.web;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.system.mng.service.ManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class ManualController extends BaseController{

    @Autowired
    ManualService manualService;

    @RequestMapping(value = "/system/management/manuals", method = RequestMethod.GET)
    public PagingResVo pagingManual(@ModelAttribute("paging") PagingReqVo paging, @ModelAttribute("search") SearchReqVo search){

        PagingResVo<Manual> resPage = manualService.pagingManual(paging, search);

        return resPage;
    }

    @Transactional
    @RequestMapping(value = "/system/management/manuals", method = RequestMethod.POST)
    public void insertManual(@RequestBody Manual manual){

        manualService.insertManual(manual);
    }

    @Transactional
    @RequestMapping(value = "/system/management/manuals", method = RequestMethod.DELETE)
    public void deleteManuals(@RequestParam Integer[] manualIds){

        manualService.deleteManuals(manualIds);
    }

    @RequestMapping(value = "/system/management/manuals/{manualId}", method = RequestMethod.GET)
    public Manual getManual(@PathVariable Integer manualId){

        Manual getResource =  manualService.getManual(manualId);

        return getResource;
    }

    @Transactional
    @RequestMapping(value = "/system/management/manuals/{manualId}", method = RequestMethod.PUT)
    public void updateManual(@PathVariable Integer manualId, @RequestBody Manual manual){

        manualService.updateManual(manualId, manual);
    }

    @Transactional
    @RequestMapping(value = "/system/management/manuals/{manualId}", method = RequestMethod.DELETE)
    public void deleteManual(@PathVariable Integer manualId){

        manualService.deleteManual(manualId);
    }
}
