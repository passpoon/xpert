package com.crossent.monitoring.portal.test;

import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.jpa.domain.TypeCode;
import com.crossent.monitoring.portal.common.lib.json.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ToJson2 {


    public static void main(String[] args){
        try {
            ObjectMapper om = ObjectMapperFactory.newInstance();


            //PagingReqVo
            Measurement measurement = new Measurement();

            List<Metric> metrics = new ArrayList<Metric>();

            TypeCode typeCode = new TypeCode();
            typeCode.setCode("0000100002");
            Metric m1 = new Metric();
            m1.setId(1);
            m1.setName("m1");
            m1.setMeasurement(measurement);
            m1.setDescription("m1 d");
            m1.setTypeCode(typeCode);

            Metric m2 = new Metric();
            m2.setId(2);
            m2.setName("m2");
            m2.setMeasurement(measurement);
            m2.setDescription("m2 d");
            m2.setTypeCode(typeCode);

            metrics.add(m1);
            metrics.add(m2);

            measurement.setId(11);
            measurement.setName("mm1");
            measurement.setDescription("mm1 d");
            measurement.setMetrics(metrics);





            String jsonSearch = om.writer().withDefaultPrettyPrinter().writeValueAsString(measurement);
            System.out.println("jsonSearch : " + jsonSearch);




        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}

