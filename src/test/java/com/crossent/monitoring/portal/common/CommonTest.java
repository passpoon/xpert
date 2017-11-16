package com.crossent.monitoring.portal.common;

import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class CommonTest extends AbstractMockTest {

    // 모니터링 그룹
    // 서버 조회


    // 어플리케이션 조회


    /*// 관리자 조회
    @Test
    public void getMongGroupMangers() throws Exception {

        Integer monitoringGroupId = 5;
        String name = "정한";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("monitoringGroupId", "1");
        params.add("name", name);

        get("/system/common/monitoring-groups/managers", params);
    }*/

    // 서버유형 조회
    public void getServerResourceServerTypes() throws Exception {

        Integer serverResourceId =  1;
        String name = "sm";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverResourceId", "1");
        params.add("name", name);

        get("/system/common/server-types", params);
    }

}
