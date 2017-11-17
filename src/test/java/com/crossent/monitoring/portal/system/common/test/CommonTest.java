package com.crossent.monitoring.portal.system.common.test;

import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class CommonTest extends AbstractMockTest {

    // 서버유형 조회
    @Test
    public void getServerTypes() throws Exception {

        Integer serverResourceId =  2;
        String name = "sm";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverResourceId", "2");
        params.add("name", name);

        get("/common/server-types", params);
    }

    // 어플리케이션 정보 조회
    @Test
    public void getAppInfos() throws Exception {

        Integer appResourceId =  1;
        String name = "my";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("appResourceId", "1");
        params.add("name", name);

        get("/common/app-infos", params);
    }
}
