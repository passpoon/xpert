package com.crossent.monitoring.portal.test;

import com.crossent.monitoring.portal.common.lib.json.ObjectMapperFactory;
import com.crossent.monitoring.portal.sample.dto.MeasurementDto;
import com.crossent.monitoring.portal.sample.dto.MetricDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapperTest {


    public static void main(String[] args){
        try {
            ObjectMapper om = ObjectMapperFactory.newInstance();


            //Object to Json
            MeasurementDto dto = new MeasurementDto();

            dto.setName("test1");
            dto.setDescription("test measurement 1");

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

            String json = om.writer().withDefaultPrettyPrinter().writeValueAsString(dto);


            System.out.println("json : " + json);


            //Json to Object
            MeasurementDto resDto = om.readValue(json, MeasurementDto.class);
            System.out.println("resDto : " + resDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
