package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.repository.AppResourceRepository;
import com.crossent.monitoring.portal.jpa.repository.ServerResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class AppResourceService {

    @Autowired
    AppResourceRepository appResourceRepository;

    public PagingResVo<AppResource> pagingAppResource(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

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
        Page<AppResource> appResources = null;
        if (key == null) {
            //TODO 전체조회
            appResources = appResourceRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "name": {
                    appResources = appResourceRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description": {
                    appResources = appResourceRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        PagingResVo<AppResource> resPage = new PagingResVo<AppResource>(appResources, true);

        return resPage;
    }

    /*public void insertAppResource(AppResource appResource) {

        AppResource in = new AppResource();
        in.setName(appResource.getName());
        in.setAppInfo(appResource.getAppInfo());
        in.setServerResource(appResource.getServerResource());
        in.setDescription(appResource.getDescription());

        AppResource resUser = appResourceRepository.save(in);
    }*/

    public void deleteAppResources(Integer[] appResourceIds) {

        appResourceRepository.deleteByIdIn(appResourceIds);
    }

    public AppResource getAppResource(Integer appResourceId) {

        AppResource resource = appResourceRepository.findOne(appResourceId);

        AppResource out = new AppResource();
        out.setId(resource.getId());
        out.setName(resource.getName());
        out.setAppInfo(resource.getAppInfo());
        out.setServerResource(resource.getServerResource());
        out.setDescription(resource.getDescription());

        return out;
    }

    /*public AppResource updateAppResource(Integer appResourceId, AppResource serverResource) {

        AppResource getData = appResourceRepository.findOne(appResourceId);

        AppInfo appInfo = new AppInfo();

        if (getData == null) {
            return null;
        }
        getData.setName(serverResource.getName());
//        getData.setAppInfo(appInfo.getId());
        getData.setDescription(serverResource.getDescription());

        AppResource updateData = appResourceRepository.save(getData);

        return updateData;
    }*/

    public void deleteAppResourceResource(Integer appResourceId) {

        appResourceRepository.delete(appResourceId);
    }
}