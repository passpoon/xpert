package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.repository.AppResourceRepository;
import com.crossent.monitoring.portal.jpa.repository.ServerResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class AppResourceService {

    private static Logger logger = LoggerFactory.getLogger(AppResourceService.class);

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
        logger.debug("key : {}", key);
        logger.debug("keyword : {}", keyword);

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
                default:
                    throw new BusinessException("unDefSearchKey", key);
            }
        }

        PagingResVo<AppResource> resPage = new PagingResVo<AppResource>(appResources, true);

        return resPage;
    }

    public void insertAppResource(AppResource appResource) {

        AppResource in = new AppResource();
        in.setName(appResource.getName());
        in.setAppInfoId(appResource.getAppInfoId());
        in.setServerResourceId(appResource.getServerResourceId());
        in.setUuid(appResource.getUuid());
        in.setDescription(appResource.getDescription());

        AppResource resUser = appResourceRepository.save(in);
    }

    public void deleteAppResources(Integer[] appResourceIds) {

        appResourceRepository.deleteByIdIn(appResourceIds);
    }

    public AppResource getAppResource(Integer appResourceId) {

        AppResource appResource = appResourceRepository.findOne(appResourceId);
        if(logger.isDebugEnabled()){
            logger.debug("appResource : {}", appResource);
        }
        if(appResource == null) {
            throw new BusinessException("noFindAppResource");
        }

        AppResource out = new AppResource();
        out.setId(appResource.getId());
        out.setName(appResource.getName());
        out.setAppInfo(appResource.getAppInfo());
        out.setServerResource(appResource.getServerResource());
        out.setUuid(appResource.getUuid());
        out.setDescription(appResource.getDescription());

        return out;
    }

    public AppResource updateAppResource(Integer appResourceId, AppResource appResource) {

        AppResource getData = appResourceRepository.findOne(appResourceId);
        if(logger.isDebugEnabled()){
            logger.debug("appResource : {}", appResource);
        }
        if(appResource == null) {
            throw new BusinessException("noFindAppResource");
        }

        getData.setName(appResource.getName());
        getData.setAppInfoId(appResource.getAppInfoId());
        getData.setServerResourceId(appResource.getServerResourceId());
        getData.setDescription(appResource.getDescription());

        AppResource updateData = appResourceRepository.save(getData);

        return updateData;
    }

    public void deleteAppResourceResource(Integer appResourceId) {

        appResourceRepository.delete(appResourceId);
    }
}