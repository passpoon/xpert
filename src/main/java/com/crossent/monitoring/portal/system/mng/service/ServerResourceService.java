package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.repository.ServerResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Service
public class ServerResourceService {

    private static Logger logger = LoggerFactory.getLogger(ServerResourceService.class);

    @Autowired
    ServerResourceRepository serverResourceRepository;

    public PagingResVo<ServerResource> pagingServerResource(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

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
        Page<ServerResource> serverResources = null;
        logger.debug("key : {}", key);
        logger.debug("keyword : {}", keyword);

        if (key == null) {
            //TODO 전체조회
            serverResources = serverResourceRepository.findAll(pagingReqVo.toPagingRequest());
        } else {
            switch (key) {
                case "name": {
                    serverResources = serverResourceRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "hostname": {
                    serverResources = serverResourceRepository.findByHostNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "ip": {
                    serverResources = serverResourceRepository.findByIpLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description": {
                    serverResources = serverResourceRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                default:
                    throw new BusinessException("unDefSearchKey", key);
            }
        }

        PagingResVo<ServerResource> resPage = new PagingResVo<ServerResource>(serverResources, true);

        return resPage;
    }

    public void insertServerResource(ServerResource serverResource) {

        ServerResource in = new ServerResource();
        in.setName(serverResource.getName());
        in.setHostName(serverResource.getHostName());
        in.setIp(serverResource.getIp());
        in.setServerTypeId(serverResource.getServerTypeId());
        in.setUuid(serverResource.getUuid());
        in.setDescription(serverResource.getDescription());

        ServerResource resUser = serverResourceRepository.save(in);
    }

    public void deleteServerResources(Integer[] serverResourceIds) {

        serverResourceRepository.deleteByIdIn(serverResourceIds);
    }

    public ServerResource getServerResource(Integer serverResourceId) {

        ServerResource serverResource = serverResourceRepository.findOne(serverResourceId);
        if(logger.isDebugEnabled()) {
            logger.debug("serverResource :: {}", serverResource);
        }
        if(serverResource == null) {
            throw new BusinessException("noFindServerResource");
        }

        ServerResource out = new ServerResource();
        out.setId(serverResource.getId());
        out.setName(serverResource.getName());
        out.setHostName(serverResource.getHostName());
        out.setIp(serverResource.getIp());
        out.setServerTypeId(serverResource.getServerTypeId());
        out.setUuid(serverResource.getUuid());
        out.setDescription(serverResource.getDescription());

        return out;
    }

    public ServerResource updateServerResource(Integer serverResourceId, ServerResource serverResource) {

        ServerResource getData = serverResourceRepository.findOne(serverResourceId);
        if(logger.isDebugEnabled()) {
            logger.debug("getData :: {}", getData);
        }
        if(getData == null) {
            throw new BusinessException("noFindServerResource");
        }

        getData.setName(serverResource.getName());
        getData.setHostName(serverResource.getHostName());
        getData.setIp(serverResource.getIp());
        getData.setServerTypeId(serverResource.getServerTypeId());
        getData.setDescription(serverResource.getDescription());

        ServerResource updateData = serverResourceRepository.save(getData);

        return updateData;
    }

    public void deleteServerResource(Integer serverResourceId) {

        serverResourceRepository.delete(serverResourceId);
    }
}