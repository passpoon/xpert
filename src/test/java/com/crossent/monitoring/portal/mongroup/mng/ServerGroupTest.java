package com.crossent.monitoring.portal.mongroup.mng;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroup;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroupCriticalValue;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ServerGroupTest extends AbstractMockTest {

    @Test
    public void pageServerGroup() throws Exception {
        Integer monitoringGroupId = 1;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "api");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups", params);
    }

    @Test
    public void createServerGroup() throws Exception {
        Integer monitoringGroupId = 1;

        MgServerGroup mgServerGroup = new MgServerGroup();
        mgServerGroup.setName("serverGroup insert");
        mgServerGroup.setServerTypeId(8);
        mgServerGroup.setDescription("서버그룹 생성 테스트2");
        mgServerGroup.setDashboardYn("N");
        mgServerGroup.setMonitoringYn("N");

        post("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups", mgServerGroup);
    }

    @Test
    public void getServerGroup() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"");
    }

    @Test
    public void updateMgServerGroup() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId =  1;

        MgServerGroup update = new MgServerGroup();
        update.setName("서버그룹 1 수정");
        update.setServerTypeId(2);
        update.setMonitoringYn("N");
        update.setDashboardYn("N");

        put("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"", update);
    }

    @Test
    public void deleteMgServerGroups() throws Exception {
        Integer monitoringGroupId = 1;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverGroupIds", "24");

        delete("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups", params);
    }

    @Test
    public void getServerGroupMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId = 19;

        get("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"/metrics");
    }

    @Test
    public void updateServerGroupMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId =  1;
        Integer metricId = 207;

        MgServerGroupCriticalValue update = new MgServerGroupCriticalValue();
        update.setWarning(65.42);
        update.setCritical(80.01);

        put("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"/metrics/"+metricId+"", update);
    }

    @Test
    public void getServerGroupServerResource() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId = 19;

        get("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"/servers");
    }

    @Test  //다중 서버리소스 추가
    public void insertServerGroupServerResources() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId = 1;
        Integer[] serverResourceIds = {1, 6}; // Mg_server에 있는 server resource를 할당

        post("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"/servers", serverResourceIds);
    }

    @Test
    public void deleteServerGroupServerResources() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId = 1;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverResourceIds", "6");

        delete("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"/servers", params);
    }

    @Test
    public void deleteServerGroupServerResource() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId = 1;
        Integer serverResourceId = 6;

        delete("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"/servers/"+serverResourceId+"");
    }
}
