package com.crossent.monitoring.portal.sample.service;

import com.crossent.monitoring.portal.jpa.domain.Test;
import com.crossent.monitoring.portal.sample.repository.SampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SampleService {
    private static Logger logger = LoggerFactory.getLogger(SampleService.class);

    @Autowired
    SampleRepository sampleRepository;

    public long createTest(){

        Test test = new Test();
        test.setCol1("col 1 입니다. " + System.currentTimeMillis());
        test.setCol2("col 2 입니다. " + System.currentTimeMillis());
        test.setCol3("col 3 입니다. " + System.currentTimeMillis());
        test.setCol4("col 4 입니다. " + System.currentTimeMillis());

        sampleRepository.save(test);

        long count = sampleRepository.count();

        logger.debug("test count :{}", count);
        return count;



    }
}
