package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import com.crossent.monitoring.portal.jpa.repository.ServerResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Service
public class ServerResourceService {

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
        in.setDescription(serverResource.getDescription());

        ServerResource resUser = serverResourceRepository.save(in);
    }

    public void deleteServerResources(Integer[] serverResourceIds) {

        serverResourceRepository.deleteByIdIn(serverResourceIds);
    }

    public ServerResource getServerResource(Integer serverResourceId) {

        ServerResource resource = serverResourceRepository.findOne(serverResourceId);

        ServerResource out = new ServerResource();
        out.setId(resource.getId());
        out.setName(resource.getName());
        out.setHostName(resource.getHostName());
        out.setIp(resource.getIp());
        out.setServerTypeId(resource.getServerTypeId());
        out.setDescription(resource.getDescription());

        return out;
    }

    public ServerResource updateServerResource(Integer serverResourceId, ServerResource serverResource) {

        ServerResource getData = serverResourceRepository.findOne(serverResourceId);

        if (getData == null) {
            return null;
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