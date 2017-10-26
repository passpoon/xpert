package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.User;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class UserTest extends AbstractMockTest {

    @Test
    public void listUser() throws Exception {
        get("/system/management/users");
    }

    @Test
    public void pageMeasurement() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(1);
        pagingReqVo.setPageSize(2);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("field1", "monuser1");
        searchReqVo.addKeyword("field2", "keyword2");
        searchReqVo.addKeyword("field3", "keyword3");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));
        get("/system/management/users/paging", params);
    }

    @Test
    public void insertUser() throws Exception {
        User dto = new User();

        dto.setId("monuser4");
        dto.setName("빼박");
        dto.setEmail("crossent@crossent.com");
        dto.setPhone("010-2222-3454");
        dto.setChatId("chat");
        dto.setDescription("test inset user");

        post("/system/management/users", dto);
    }


}
