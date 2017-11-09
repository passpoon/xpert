package com.crossent.monitoring.portal.mongroup.mng;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.MgApp;
import com.crossent.monitoring.portal.jpa.domain.MgAppCriticalValue;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ApplicationTest extends AbstractMockTest {

    @Test
    public void pageServer() throws Exception {
        Integer monitoringGroupId = 1;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        /*searchReqVo.addKeyword("serverResourceName", "mon");*/

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/monitoring-groups/"+monitoringGroupId+"/management/app/apps", params);
    }

    @Test
    public void updateApp() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId =  1;

        MgApp update = new MgApp();
        update.setDashboardYn("Y");
        update.setMonitoringYn("Y");

        put("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"", update);
    }

    @Test
    public void getAppMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/metrics");
    }

    @Test
    public void updateServerMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId =  1;
        Integer metricId = 207;

        MgAppCriticalValue update = new MgAppCriticalValue();
        update.setWarning(80.0);
        update.setCritical(100.0);

        put("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/metrics/"+metricId+"", update);
    }


}
