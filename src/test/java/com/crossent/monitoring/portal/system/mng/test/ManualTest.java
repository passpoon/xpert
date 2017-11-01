package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.jpa.domain.ServerResource;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ManualTest extends AbstractMockTest {

    @Test
    public void pageManual() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "aa");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/manuals", params);
    }

    @Test
    public void insertManual() throws  Exception {

        Manual manual = new Manual();
        manual.setName("bbb");
        manual.setLink("http://www.aaa.com");
        manual.setDescription("test");

        post("/system/management/manuals", manual);
    }

    @Test
    public void deleteServerResources() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("manualIds", "4,5");

        delete("/system/management/manuals", params);
    }

    @Test
    public void getServerResource() throws Exception {
        Integer manualIds = 1;

        get("/system/management/manuals/" + manualIds);
    }

    @Test
    public void updateManual() throws Exception {
        Integer manualIds = 3;

        Manual dto = new Manual();
        dto.setName("test1");
        dto.setLink("http://crossent.com");
        dto.setDescription("test Des");

        put("/system/management/manuals/" + manualIds, dto);
    }

    @Test
    public void deleteManual() throws Exception {
        Integer manualId = 3;

        delete("/system/management/manuals/" + manualId);
    }
}
