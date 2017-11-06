package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Manual;
import com.crossent.monitoring.portal.jpa.domain.Meta;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MetaTest extends AbstractMockTest {

    @Test
    public void pageMeta() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("prog", "ap");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/meta", params);
    }

    @Test
    public void insertMeta() throws  Exception {

        Meta meta = new Meta();
        meta.setProg("zzz");
        meta.setManualId(2);
        meta.setStartPoint(11);
        meta.setEndPoint(22);
        meta.setLogSource("ddbbbb");
        meta.setPattern("ddd");
        meta.setStateCodeId("400");

        post("/system/management/meta", meta);
    }

    @Test
    public void deleteMetas() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("metaIds", "14,15");

        delete("/system/management/meta", params);
    }

    @Test
    public void getMeta() throws Exception {
        Integer metaId = 11;

        get("/system/management/meta/" + metaId);
    }

    @Test
    public void updateMeta() throws Exception {
        Integer metaId = 17;

        Meta dto = new Meta();
        dto.setProg("bbb");
        dto.setPattern("aaa");
        dto.setLogSource("yyy");
        dto.setStartPoint(456);
        dto.setEndPoint(457);
        dto.setManualId(2);
        dto.setStateCodeId("200");

        put("/system/management/meta/" + metaId, dto);
    }

    @Test
    public void deleteManual() throws Exception {
        Integer metaId = 11;

        delete("/system/management/meta/" + metaId);
    }
}
