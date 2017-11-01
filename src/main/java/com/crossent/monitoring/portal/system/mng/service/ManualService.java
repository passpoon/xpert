package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.jpa.repository.ManualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class ManualService {

    @Autowired
    ManualRepository manualRepository;

    public PagingResVo<Manual> pagingManual(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if (keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<Manual> manuals = null;
        if (key == null) {
            //TODO 전체조회
            manuals = manualRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "name": {
                    manuals = manualRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "link": {
                    manuals = manualRepository.findByLinkLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description": {
                    manuals = manualRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        PagingResVo<Manual> resPage = new PagingResVo<Manual>(manuals, true);

        return resPage;
    }

    public void insertManual(Manual manual) {

        Manual in = new Manual();
        in.setName(manual.getName());
        in.setLink(manual.getLink());
        in.setDescription(manual.getDescription());

        Manual resUser = manualRepository.save(in);
    }

    public void deleteManuals(Integer[] manualIds) {

        manualRepository.deleteByIdIn(manualIds);
    }

    public Manual getManual(Integer manualId) {

        Manual manual = manualRepository.findOne(manualId);

        Manual out = new Manual();
        out.setId(manual.getId());
        out.setName(manual.getName());
        out.setLink(manual.getLink());
        out.setDescription(manual.getDescription());

        return out;
    }

    public Manual updateManual(Integer manualId, Manual manual) {

        Manual getData = manualRepository.findOne(manualId);

        if (getData == null) {
            return null;
        }
        getData.setName(manual.getName());
        getData.setLink(manual.getLink());
        getData.setDescription(manual.getDescription());

        Manual updateData = manualRepository.save(getData);

        return updateData;
    }

    public void deleteManual(Integer manualId) {

        manualRepository.delete(manualId);
    }
}