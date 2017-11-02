package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class AppResourceTest extends AbstractMockTest {

    @Test
    public void pageAppResource() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "app");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/apps", params);
    }

    @Test
    public void insertAppResource() throws  Exception {

        AppResource appResource = new AppResource();
        appResource.setName("aaa");
        appResource.setAppInfoId(2);
        appResource.setServerResourceId(5);
        appResource.setDescription("ccc");

        post("/system/management/apps", appResource);
    }

    @Test
    public void deleteAppResources() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("appResourceIds", "4,6");

        delete("/system/management/apps", params);
    }

   @Test
    public void getAppResource() throws Exception {
        Integer appResourceId = 1;

        get("/system/management/apps/" + appResourceId);
    }

    @Test
    public void updateAppResource() throws Exception {
        Integer appResourceId = 7;

        AppResource dto = new AppResource();
        dto.setName("test1");
        dto.setAppInfoId(1);
        dto.setServerResourceId(3);
        dto.setDescription("test Des");

        put("/system/management/apps/" + appResourceId, dto);
    }

    @Test
    public void deleteServerResource() throws Exception {
        Integer appResourceId = 3;

        delete("/system/management/apps/" + appResourceId);
    }
}
