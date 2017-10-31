package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.User;
import com.crossent.monitoring.portal.jpa.domain.UserGroup;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class UserGroupTest extends AbstractMockTest {

    @Test
    public void pageUserGroup() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "ad");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/user-groups", params);

    }

    @Test
    public void insertUserGroup() throws  Exception {

        UserGroup userGroup = new UserGroup();
        userGroup.setId("bbb");
        userGroup.setName("aaa");
        userGroup.setDescription("ccc");

        post("/system/management/user-groups", userGroup);
    }

    @Test
    public void deleteUserGroups() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userGroupIds", "aaa,bbb");

        delete("/system/management/user-groups", params);
    }

    @Test
    public void getUserGroup() throws Exception {
        String userId = "admin";

        get("/system/management/user-groups/" + userId);
    }

    @Test
    public void updateUserGroup() throws Exception {
        String userGroupId = "user";

        UserGroup dto = new UserGroup();
        dto.setId(userGroupId);
        dto.setName("user");
        dto.setDescription("사용자 그룹");

        put("/system/management/user-groups/" + userGroupId, dto);
    }

    @Test
    public void deleteUserGroup() throws Exception {
        String userGroupId = "bbb";

        delete("/system/management/user-groups/" + userGroupId);
    }

    @Test
    public void getUserGroupUsers() throws Exception {
        String userGroupId = "admin";

        get("/system/management/user-groups/"+userGroupId+"/users");
    }

    @Test
    public void insertUserGroupUser() throws Exception {
        String userGroupId = "admin";
        User user = new User();
        user.setId("monuser11");

        post("/system/management/user-groups/"+userGroupId+"/users", user);
    }

    @Test
    public void deleteUserGroupUsers() throws Exception {
        String userGroupId = "admin";

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userIds", "monuser1,monuser11");

        delete("/system/management/user-groups/"+userGroupId+"/users", params);
    }

    @Test
    public void deleteUserGroupUser() throws Exception {
        String userGroupId = "admin";
        String userId = "monuser11";

        delete("/system/management/user-groups/"+userGroupId+"/users/"+userId+"");
    }

}
