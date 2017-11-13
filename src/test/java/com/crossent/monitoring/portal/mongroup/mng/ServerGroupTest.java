package com.crossent.monitoring.portal.mongroup.mng;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.MgServerGroup;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ServerGroupTest extends AbstractMockTest {

    @Test
    public void pageServerGroup() throws Exception {
        Integer monitoringGroupId = 2;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "서버");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups", params);
    }

    @Test
    public void createServerGroup() throws Exception {
        Integer monitoringGroupId = 1;

        MgServerGroup mgServerGroup = new MgServerGroup();
        mgServerGroup.setName("serverGroup create");
        mgServerGroup.setServerTypeId(2);
        mgServerGroup.setDescription("서버그룹 생성 테스트");
        mgServerGroup.setDashboardYn("N");
        mgServerGroup.setMonitoringYn("N");

        post("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups", mgServerGroup);
    }

    @Test
    public void updateServerGroup() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverGroupId =  1;

        MgServer update = new MgServer();
        update.setMonitoringYn("N");
        update.setDashboardYn("N");

        put("/monitoring-groups/"+monitoringGroupId+"/management/server-group/server-groups/"+serverGroupId+"", update);
    }

    /*@Test
    public void getServerGroupMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/metrics");
    }

    @Test
    public void updateServerMetrics() throws Exception {
        Integer monitoringGroupId = 2;
        Integer serverResourceId =  3;
        Integer metricId = 116;

        MgServerCriticalValue update = new MgServerCriticalValue();
        update.setWarning(65.42);
        update.setCritical(80.01);

        put("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/metrics/"+metricId+"", update);
    }*/

}
