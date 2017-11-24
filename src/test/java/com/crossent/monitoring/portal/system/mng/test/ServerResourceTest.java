package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ServerResourceTest extends AbstractMockTest {

    @Test
    public void pageServerResource() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
//        searchReqVo.addKeyword("hostname", "age");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/servers", params);
    }

    @Test
    public void insertServerResource() throws  Exception {

        ServerResource serverResource = new ServerResource();
        serverResource.setName("name");
        serverResource.setHostName("host");
        serverResource.setIp("10.10.10.10");
        serverResource.setServerTypeId(2);
        serverResource.setUuid("aa");
        serverResource.setDescription("ccc");

        post("/system/management/servers", serverResource);
    }

    @Test
    public void deleteServerResources() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverResourceIds", "8,9");

        delete("/system/management/servers", params);
    }

    @Test
    public void getServerResource() throws Exception {
        Integer serverResourceId = 1;

        get("/system/management/servers/" + serverResourceId);
    }

    @Test
    public void updateServerResource() throws Exception {
        Integer serverResourceId = 7;

        ServerResource dto = new ServerResource();
        dto.setIp("10.10.10.80");
        dto.setName("test1");
        dto.setHostName("test11");
        dto.setServerTypeId(2);
        dto.setDescription("test Des");

        put("/system/management/servers/" + serverResourceId, dto);
    }

    @Test
    public void deleteServerResource() throws Exception {
        Integer serverResourceId = 7;

        delete("/system/management/servers/" + serverResourceId);
    }
}
