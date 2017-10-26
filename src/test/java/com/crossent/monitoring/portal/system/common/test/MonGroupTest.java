package com.crossent.monitoring.portal.system.common.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonGroupTest extends AbstractMockTest {

    @Test
    public void listMonGroup() throws Exception {

        String userId = "monuser1";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        super.get("/system/common/monitoring-groups", params);
    }

    @Test
    public void listUsers() throws Exception {
        String uuid = "d490c2b6-5649-4138-ae51-636702c40092";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("uuid", uuid);
        super.get("/system/common/user", params);
    }
}