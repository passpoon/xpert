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
    public void pageMonServer() throws Exception {
        Integer monitoringGroupId = 1;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("applicationName", "aa");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/monitoring-groups/"+monitoringGroupId+"/management/app/apps", params);
    }

    @Test
    public void updateMonApp() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId =  1;

        MgApp update = new MgApp();
        update.setDashboardYn("Y");
        update.setMonitoringYn("Y");

        put("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"", update);
    }

    @Test
    public void getMonAppMeasurement() throws Exception {

        Integer monitoringGroupId = 1;
        Integer appResourceId = 18;

        get("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/measurements");
    }

    @Test
    public void insertMonAppMeasurement() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId = 18;
        Integer[] measurementIds = {6};

        post("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/measurements", measurementIds);
    }

    @Test
    public void deleteMonAppMeasurements() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId = 18;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("measurementIds", "6");

        delete("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/measurements", params);
    }

    @Test
    public void getMonAppMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/metrics");
    }

    @Test
    public void insertMonAppMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId =  19;
        Integer[] metricIds = {5};

        post("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/metrics", metricIds);
    }

    @Test
    public void updateMonServerMetrics() throws Exception {
        Integer monitoringGroupId = 2;
        Integer appResourceId =  4;
        Integer metricId = 54 ;

        MgAppCriticalValue update = new MgAppCriticalValue();
        update.setWarning(65.42);
        update.setCritical(80.01);

        put("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/metrics/"+metricId+"", update);
    }

    @Test
    public void deleteMonServerMetrics() throws Exception {
        Integer monitoringGroupId = 1;
        Integer appResourceId =  19;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("metricIds", "5");

        delete("/monitoring-groups/"+monitoringGroupId+"/management/app/apps/"+appResourceId+"/metrics", params);
    }
}
