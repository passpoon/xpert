package com.crossent.monitoring.portal.mongroup.monitoring;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonDashboardTest extends AbstractMockTest {

    @Test
    public void getServerStatistics() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/server-statistics");
    }

    @Test
    public void pageServerStatuses() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/server-statuses");
    }

    @Test
    public void pageServerGroupStatuses() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/server-group-statuses");
    }


    @Test
    public void getAppStatistics() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/apps-statistics");
    }


    @Test
    public void listAppStatuses() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/app-statuses");
    }


    @Test
    public void listAppGroupStatuses() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/app-group-statuses");
    }


    @Test
    public void listEvent() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/events");
    }


    @Test
    public void listAlarm() throws Exception {
        Integer monitoringGroupId = 1;
        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/dashboard/alarms");
    }


}
