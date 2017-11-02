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
    public void pageUser() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("null", "null");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));
        get("/system/management/users", params);
    }

    @Test
    public void insertUser() throws Exception {
        User dto = new User();

        dto.setId("monuser11");
        dto.setName("빼박");
        dto.setEmail("crossent@crossent.com");
        dto.setPhone("010-2222-3454");
        dto.setChatId("chat");
        dto.setDescription("test inset user");
        dto.setUuid("dddddddd");

        post("/system/management/users", dto);
    }

    @Test
    public void deleteUsers() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userIds", "test3,test4");

        delete("/system/management/users", params);
    }

    @Test
    public void getUser() throws Exception {
        String userId = "monuser1";
        get("/system/management/users/" + userId);
    }

    @Test
    public void updateUser() throws Exception {
        String userId = "monuser4";

        User dto = new User();
        dto.setId(userId);
        dto.setName("감사합니다");
        dto.setEmail("crossent@crossent.com");
        dto.setPhone("010-2222-3454");
        dto.setChatId("chat");
        dto.setDescription("test inset user");
        dto.setUuid("dddddddd");

        put("/system/management/users/"+ userId, dto);
    }

    @Test
    public void deleteUser() throws Exception {

        String userId = "test2";
        delete("/system/management/users/" +userId);
    }


}
