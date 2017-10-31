package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ServerTypeTest extends AbstractMockTest{


    @Test
    public void pageServerType() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "Bi");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/server-types", params);
    }

    @Test
    public void insertServerType() throws  Exception {

        ServerType serverType = new ServerType();
        serverType.setName("aaa");
        serverType.setDescription("bbbb");

        post("/system/management/server-types", serverType);
    }

/*    @Test   // LinkedMultiValueMap String, Integer 추가
    public void deleteServerTypes() throws  Exception {

        LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<String, Integer>();
        params.add("serverTypeIds", "3,4");

        delete("/system/management/server-types", params);
    }*/

    @Test
    public void getServerType() throws Exception {
        Integer serverTypeId = 2;

        get("/system/management/server-types/" + serverTypeId);
    }

    @Test
    public void updateServerType() throws Exception {
        Integer serverTypeId = 2;

        ServerType dto = new ServerType();
        dto.setName("small");
        dto.setDescription("서버타입 수정");

        put("/system/management/server-types/" + serverTypeId, dto);
    }

    @Test
    public void deleteServerType() throws Exception {
        Integer serverTypeId = 4;

        delete("/system/management/server-types/" + serverTypeId);
    }



}
