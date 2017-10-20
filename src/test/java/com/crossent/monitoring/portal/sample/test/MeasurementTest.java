package com.crossent.monitoring.portal.sample.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeasurementTest {
    private Logger logger = LoggerFactory.getLogger(MeasurementTest.class);

    String url = "/api/monitoring/v1.0/sample";

    @Autowired
    private MockMvc mvc;

    @Test
    public void listMeasurement() throws Exception {

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(url + "/measurements").accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();

        logger.info("content : {}", result.getResponse().getContentAsString());
    }

    @Test
    public void selectMeasurement() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(url + "/measurements/2").accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();

        logger.info("content : {}", result.getResponse().getContentAsString());

    }

    @Test
    public void insertMeasurement() throws Exception {
        //ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(url + "/measurements").accept(MediaType.APPLICATION_JSON));
        String reqJson = JsonUtil.ObjectToJson(makeMeasurementDto());
        logger.debug("req json : {}", reqJson);
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(url + "/measurements").contentType(MediaType.APPLICATION_JSON).content(reqJson));

        resultActions.andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();

        logger.info("content : {}", result.getResponse().getContentAsString());
    }


    @Test
    public void pageMeasurement() throws Exception {

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(url + "/measurements/paging").accept(MediaType.APPLICATION_JSON)
                .param("paging", "{\"page\":2,\"pageSize\":3}")
        .param("search", "{\"keywords\":{\"field1\":\"keyword1\",\"field2\":\"keyword2\",\"field3\":" +
                "\"keyword3\"},\"rangeType\":null,\"rangeStart\":null,\"rangeEnd\":null}"));

        resultActions.andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();

        logger.info("content : {}", result.getResponse().getContentAsString());
    }



    public MeasurementDto makeMeasurementDto() {
        MeasurementDto dto = new MeasurementDto();

        dto.setName("test9");
        dto.setDescription("test measurement 9");

        List<MetricDto> metrics = new ArrayList<MetricDto>();

        MetricDto m1 = new MetricDto();
        m1.setName("m1");
        m1.setMetricTypeCode("0000100001");
        m1.setDescription("test m1");

        MetricDto m2 = new MetricDto();
        m2.setName("m2");
        m2.setMetricTypeCode("0000100002");
        m2.setDescription("test m2");

        metrics.add(m1);
        metrics.add(m2);

        dto.setMetrics(metrics);

        return dto;

    }
}