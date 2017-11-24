package com.crossent.monitoring.portal.mongroup.mng;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.*;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ApplicationGroupTest extends AbstractMockTest {

    @Test
    public void pageAppGroup() throws Exception {
        Integer monitoringGroupId = 2;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups", params);
    }

    @Test
    public void createAppGroup() throws Exception {
        Integer monitoringGroupId = 1;

        MgAppGroup mgAppGroup = new MgAppGroup();
        mgAppGroup.setName("appGroup insert");
        mgAppGroup.setAppInfoId(3);
        mgAppGroup.setDescription("어플리케이션 생성 테스트");
        mgAppGroup.setDashboardYn("N");
        mgAppGroup.setMonitoringYn("N");

        post("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups", mgAppGroup);
    }

    @Test
    public void updateAppGroup() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appGroupId =  3;

        MgAppGroup update = new MgAppGroup();
        update.setName("aaa");
        update.setAppInfoId(1);
        update.setMonitoringYn("Y");
        update.setDashboardYn("Y");

        put("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups/"+appGroupId+"", update);
    }

    @Test
    public void getAppGroupMetrics() throws Exception {
        Integer monitoringGroupId = 3;
        Integer appGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups/"+appGroupId+"/metrics");
    }

    @Test
    public void updateAppGroupMetrics() throws Exception {
        Integer monitoringGroupId = 3;
        Integer appGroupId =  1;
        Integer metricId = 207;

        MgAppGroupCriticalValue update = new MgAppGroupCriticalValue();
        update.setWarning(65.42);
        update.setCritical(80.01);

        put("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups/"+appGroupId+"/metrics/"+metricId+"", update);
    }

    @Test
    public void getAppGroupServerResource() throws Exception {
        Integer monitoringGroupId = 3;
        Integer appGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups/"+appGroupId+"/apps");
    }

    @Test
    public void insertAppGroupAppResources() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appGroupId = 1;
        Integer[] appResourceIds = {1}; // Mg_server에 있는 server resource를 할당

        post("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups/"+appGroupId+"/apps", appResourceIds);
    }

    @Test
    public void deleteAppGroupAppResource() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appGroupId = 1;
        Integer appResourceId = 1;

        delete("/monitoring-groups/"+monitoringGroupId+"/management/app-group/app-groups/"+appGroupId+"/apps/"+appResourceId+"");
    }

}
