package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class MeasurementTest extends AbstractMockTest{

    @Test
    public void pageMeasurement() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "cp");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/measurements", params);
    }

    @Test
    public void insertMeasurement() throws  Exception {

        Measurement measurement = new Measurement();
        measurement.setName("insert");
        measurement.setDescription("test");

        post("/system/management/measurements", measurement);
    }

/*    @Test
    public void insertMeasurement() throws Exception {
        MeasurementDto dto = new MeasurementDto();

        dto.setName("test11"); // 중복X
        dto.setDescription("inset Test");

            List<MetricDto> metrics = new ArrayList<MetricDto>();
            MetricDto m1 = new MetricDto();
            m1.setName("m1");
            m1.setMetricTypeCode("0000100001");
            m1.setDescription("test 1번");

            MetricDto m2 = new MetricDto();
            m2.setName("m2");
            m2.setMetricTypeCode("0000100002");
            m2.setDescription("test 2번");

            metrics.add(m1);
            metrics.add(m2);

        dto.setMetrics(metrics);

        post("/system/management/measurements", dto);
    }*/

    @Test
    public void deleteMeasurements() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("measurementIds", "52,54");

        delete("/system/management/measurements", params);
    }

    @Test
    public void getMeasurement() throws Exception {

        Integer measurementId = 50;

        get("/system/management/measurements/" + measurementId);
    }

    @Test
    public void updateMeasurement() throws Exception {

        Integer measurementId = 50;

        Measurement dto = new Measurement();
        dto.setId(measurementId);
        dto.setName("user");
        dto.setDescription("update Test");

        put("/system/management/measurements/" + measurementId, dto);
    }

    @Test
    public void deleteMeasurement() throws Exception {

        Integer measurementId = 50;

        delete("/system/management/measurements/" + measurementId);
    }

    @Test
    public void getMeasurementMetrics() throws Exception {

        Integer measurementId = 10;

        get("/system/management/measurements/"+measurementId+"/metrics");
    }

    @Test
    public void insertMeasurementMetric() throws Exception {

        Integer measurementId = 49;

        Metric metric = new Metric();
        metric.setName("m5");
        metric.setDescription("테스트입니다.");
        metric.setMetricTypeCode("0000100001");

        post("/system/management/measurements/"+measurementId+"/metrics", metric);
    }

    @Test
    public void deleteMeasurementMetrics() throws Exception {
        Integer measurementId = 49;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("metricIds", "204,205");

        delete("/system/management/measurements/"+measurementId+"/metrics", params);
    }

    @Test
    public void deleteMeasurementMetric() throws Exception {
        Integer measurementId = 49;
        Integer metricId = 203;

        delete("/system/management/measurements/"+measurementId+"/metrics/"+metricId+"");
    }
}
