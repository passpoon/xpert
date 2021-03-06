
package com.crossent.monitoring.portal.sample.web;


import com.crossent.monitoring.portal.common.web.BaseController;
import com.crossent.monitoring.portal.sample.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class    TestController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    TestService testService;

    @RequestMapping(value = "/test/influxdb", method = RequestMethod.GET)
    public void testInfluxDb() {
        testService.testInfluxdb();
    }

    @RequestMapping(value = "/test/elasticsearch", method = RequestMethod.GET)
    public void testElasticSearch() {
        testService.testElasticSearch();
    }


    @RequestMapping(value = "/test/properties", method = RequestMethod.GET)
    public void testProperties() {

        testService.testProperties();

    }


    @RequestMapping(value = "/test/dynamicquery", method = RequestMethod.GET)
    public void testDinamicQuery() {

        testService.testDynamicQuery();

    }

}

