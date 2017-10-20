package com.crossent.monitoring.portal.sample.web;

import com.crossent.monitoring.portal.sample.service.SampleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(description="모니터리 개발 샘플")
@RequestMapping(value = "/api/monitoring/${monitoring.api.version}/sample")
public class SampleController {

    @Autowired
    SampleService sampleService;

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String createTest() {
        long count = sampleService.createTest();

        return "Test컬럼 " + count + "개 생성";

    }

}
