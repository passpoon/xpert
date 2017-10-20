package com.crossent.monitoring.portal.sample.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.sample.dto.MeasurementDto;
import com.crossent.monitoring.portal.sample.dto.MetricDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MeasurementTest extends AbstractMockTest{

    @Test
    public void listMeasurement() throws Exception {
        get("/sample/measurements");
    }

    @Test
    public void selectMeasurement() throws Exception {
        String measuremetnId = "2";
        get("/sample/measurements/" + measuremetnId);
    }

    @Test
    public void insertMeasurement() throws Exception {
        MeasurementDto dto = new MeasurementDto();

        dto.setName("test10");
        dto.setDescription("test measurement 10");

        List<MetricDto> metrics = new ArrayList<MetricDto>();
        MetricDto m1 = new MetricDto();
        m1.setName("m1");
        m1.setMetricTypeCode("0000100001");
        m1.setDescription("test m11");

        MetricDto m2 = new MetricDto();
        m2.setName("m2");
        m2.setMetricTypeCode("0000100002");
        m2.setDescription("test m12");

        metrics.add(m1);
        metrics.add(m2);

        dto.setMetrics(metrics);


        post("/sample/measurements/", dto);


    }


    @Test
    public void pageMeasurement() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(2);
        pagingReqVo.setPageSize(3);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("field1", "keyword1");
        searchReqVo.addKeyword("field2", "keyword2");
        searchReqVo.addKeyword("field3", "keyword3");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));
        get("/sample/measurements/paging", params);
    }




}