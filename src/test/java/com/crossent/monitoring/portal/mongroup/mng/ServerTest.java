package com.crossent.monitoring.portal.mongroup.mng;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.AppResource;
import com.crossent.monitoring.portal.jpa.domain.MgServer;
import com.crossent.monitoring.portal.jpa.domain.MgServerCriticalValue;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class ServerTest extends AbstractMockTest {

    @Test
    public void pageMonServer() throws Exception {
        Integer monitoringGroupId = 1;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("serverResourceName", "agent1");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/monitoring-groups/"+monitoringGroupId+"/management/server/servers", params);
    }

    @Test
    public void updateMonServer() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceId =  1;

        MgServer update = new MgServer();
        update.setMonitoringYn("N");
        update.setDashboardYn("N");

        put("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"", update);
    }

    @Test
    public void getMonServerMeasurement() throws Exception {

        Integer monitoringGroupId = 1;
        Integer serverResourceId = 19;

        get("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/measurements");
    }

    @Test
    public void insertMonServerMeasurement() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceId = 19;
        Integer[] measurementIds = {71};

        post("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/measurements", measurementIds);
    }

    @Test
    public void deleteMonServerMeasurements() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceId = 19;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("measurementIds", "71");

        delete("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/measurements", params);
    }

    @Test
    public void getMonServerMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/metrics");
    }

    @Test
    public void insertMonServerMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceId =  19;
        Integer[] metricIds = {5};

        post("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/metrics", metricIds);
    }

    @Test
    public void updateMonServerMetrics() throws Exception {
        Integer monitoringGroupId = 2;
        Integer serverResourceId =  4;
        Integer metricId = 54 ;

        MgServerCriticalValue update = new MgServerCriticalValue();
        update.setWarning(65.42);
        update.setCritical(80.01);

        put("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/metrics/"+metricId+"", update);
    }

    @Test
    public void deleteMonServerMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer serverResourceId =  19;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("metricIds", "5");

        delete("/monitoring-groups/"+monitoringGroupId+"/management/server/servers/"+serverResourceId+"/metrics", params);
    }
}
