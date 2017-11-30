package com.crossent.monitoring.portal.sample.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestControllerTest extends AbstractMockTest{
    private static Logger logger = LoggerFactory.getLogger(TestControllerTest.class);

    @Test
    public void testInfluxDb() throws Throwable {
        logger.debug("system : {}" , System.getProperties());
        get("/test/influxdb");
    }


    @Test
    public void testElasticsearch() throws Throwable {
        get("/test/elasticsearch");
    }

    @Test
    public void testProperties() throws Throwable{
        get("/test/properties");
    }


}