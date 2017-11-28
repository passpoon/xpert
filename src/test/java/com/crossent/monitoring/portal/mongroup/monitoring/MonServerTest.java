package com.crossent.monitoring.portal.mongroup.monitoring;

import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import org.junit.Test;

public class MonServerTest extends AbstractMockTest {

    @Test
    public void getServerStatuses() throws Exception {
        Integer monitoringGroupId = 2;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/server/server-statuses");
    }
}
