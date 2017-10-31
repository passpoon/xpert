package com.crossent.monitoring.portal.system.mng.service;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.jpa.repository.ServerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class ServerTypeService  {

    @Autowired
    ServerTypeRepository serverTypeRepository;

    public PagingResVo<ServerType> pagingServerType(PagingReqVo pagingReqVo, SearchReqVo searchReqVo) {

        Map<String, String> keywords = searchReqVo.getKeywords();
        String key = null;
        String keyword = null;
        if(keywords != null) {
            Iterator<String> keys = keywords.keySet().iterator();
            while (keys.hasNext()) {
                key = keys.next();
                keyword = keywords.get(key);
                keyword = "%" + keyword + "%";
            }
        }
        Page<ServerType> serverTypes = null;
        if(key == null){
            //TODO 전체조회
            serverTypes = serverTypeRepository.findAll(pagingReqVo.toPagingRequest());
        }else{
            switch (key){
                case "name":
                {
                    serverTypes = serverTypeRepository.findByNameLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
                case "description":
                {
                    serverTypes = serverTypeRepository.findByDescriptionLike(pagingReqVo.toPagingRequest(), keyword);
                }
                break;
            }
        }

        PagingResVo<ServerType> resPage = new PagingResVo<ServerType>(serverTypes, true);

        return resPage;
    }

    public void insertServerType(ServerType serverType) {

        ServerType inServerType = new ServerType();
        inServerType.setName(serverType.getName());
        inServerType.setDescription(serverType.getDescription());

        ServerType resUser = serverTypeRepository.save(inServerType);
    }

    public void deleteServerTypes(Integer[] serverTypeIds) {

        serverTypeRepository.deleteByIdIn(serverTypeIds);
    }

    public ServerType getServerType(Integer serverTypeId) {

        ServerType serverType = serverTypeRepository.findOne(serverTypeId);

        ServerType out = new ServerType();
        out.setId(serverType.getId());
        out.setName(serverType.getName());
        out.setDescription(serverType.getDescription());

        return out;
    }

    public ServerType updateServerType(Integer serverTypeId, ServerType serverType) {

        ServerType getData = serverTypeRepository.findOne(serverTypeId);

        if(getData == null) {
            return null;
        }
        getData.setName(serverType.getName());
        getData.setDescription(serverType.getDescription());

        ServerType updateData = serverTypeRepository.save(getData);

        return updateData;
    }

    public void deleteServerType(Integer userGroupId) {

        serverTypeRepository.delete(userGroupId);
    }

}
