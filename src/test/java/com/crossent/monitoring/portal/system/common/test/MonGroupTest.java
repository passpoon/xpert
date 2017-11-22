package com.crossent.monitoring.portal.system.common.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonGroupTest extends AbstractMockTest {

    @Test
    public void listMonGroup() throws Exception {

        String userId = "monuser5";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        super.get("/system/common/monitoring-groups", params);
    }

    @Test
    public void listUsers() throws Exception {
        String uuid = "35b268fb-6707-4a23-8db7-ffece8feb78e";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("uuid", uuid);
        super.get("/system/common/user", params);
    }
}