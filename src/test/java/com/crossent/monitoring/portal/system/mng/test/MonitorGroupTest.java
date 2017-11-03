package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.User;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonitorGroupTest extends AbstractMockTest {

    @Test
    public void pageMonGroup() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("description", "pa");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));
        get("/system/management/monitoring-groups", params);
    }

    @Test
    public void insertMonGroup() throws Exception {

        MonGroup insert = new MonGroup();

        insert.setName("테스트2");
        insert.setDescription("모니터링 그룹 등록");

        post("/system/management/monitoring-groups", insert);
    }

    @Test
    public void deleteMonGroups() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("monGroupIds", "5,6");

        delete("/system/management/monitoring-groups", params);
    }

    @Test
    public void getMonGroup() throws Exception {

        Integer monitoringGroupId = 1;

        get("/system/management/monitoring-groups/" + monitoringGroupId);
    }

    @Test
    public void updateMonGroup() throws Exception {

        Integer monitoringGroupId = 7;

        MonGroup update = new MonGroup();
        update.setName("업데이트");
        update.setDescription("test update monitoringGroup");

        put("/system/management/monitoring-groups/"+ monitoringGroupId, update);
    }

    @Test
    public void deleteMonGroup() throws Exception {

        Integer monitoringGroupId = 7;

        delete("/system/management/monitoring-groups/" +monitoringGroupId);
    }

    @Test
    public void getMonGroupServers() throws Exception {

        Integer monitoringGroupId =  3;

        get("/system/management/monitoring-groups/"+monitoringGroupId+"/servers");
    }

    @Test
    public void insertMonGroupServers() throws Exception {
        Integer monitoringGroupId = 1;
        Integer[] serverResourceIds = {3, 4};

        post("/system/management/monitoring-groups/"+monitoringGroupId+"/servers", serverResourceIds);
    }

    @Test
    public void deleteMonGroupServers() throws Exception {
        Integer monitoringGroupId = 1;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverResourceIds", "3,4");

        delete("/system/management/monitoring-groups/"+monitoringGroupId+"/servers", params);
    }

    @Test
    public void deleteMonGroupServer() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceIds = 2;

        delete("/system/management/monitoring-groups/"+monitoringGroupId+"/servers/"+serverResourceIds+"");
    }
}
