package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
import com.crossent.monitoring.portal.jpa.domain.ServerTypeCriticalValue;
import com.crossent.monitoring.portal.system.mng.dto.MeasurementDto;
import com.crossent.monitoring.portal.system.mng.dto.ServerTypeDto;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class ServerTypeTest extends AbstractMockTest{


    @Test
    public void pageServerType() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        /*searchReqVo.addKeyword("name", "sm");*/

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/server-types", params);
    }

    @Test
    public void insertServerType() throws  Exception {

        ServerTypeDto serverType = new ServerTypeDto();
        serverType.setName("abc");
        serverType.setDescription("bb");

        /*List<MeasurementDto> measurementDtoList = new ArrayList<MeasurementDto>();

        MeasurementDto measurementDto = new MeasurementDto();
        measurementDto.setId(1);
        measurementDtoList.add(measurementDto);

        measurementDto = new MeasurementDto();
        measurementDto.setId(2);
        measurementDtoList.add(measurementDto);

        serverType.setMeasurements(measurementDtoList);*/

        post("/system/management/server-types", serverType);
    }

    @Test   // LinkedMultiValueMap String, Integer 추가
    public void deleteServerTypes() throws  Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverTypeIds", "22,23");

        delete("/system/management/server-types", params);
    }

    @Test
    public void getServerType() throws Exception {

        Integer serverTypeId = 2;

        get("/system/management/server-types/" + serverTypeId);
    }

    @Test
    public void updateServerType() throws Exception {

        Integer serverTypeId = 2;

        ServerType dto = new ServerType();
        dto.setName("small");
        dto.setDescription("서버타입 수정");

        put("/system/management/server-types/" + serverTypeId, dto);
    }

    @Test
    public void deleteServerType() throws Exception {

        Integer serverTypeId = 19;

        delete("/system/management/server-types/" + serverTypeId);
    }

    @Test
    public void getServerTypeMeasurement() throws Exception {

        Integer serverTypeId = 2;

        get("/system/management/server-types/"+serverTypeId+"/measurements");
    }

    @Test  //다중 메저먼트 추가
    public void insertServerTypeMeasurement() throws Exception {
        Integer serverTypeId = 21;
        Integer[] measurementIds = {47, 48};

        post("/system/management/server-types/"+serverTypeId+"/measurements", measurementIds);
    }

    @Test
    public void deleteServerTypeMeasurements() throws Exception {
        Integer serverTypeId = 2;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("measurementIds", "47,48");

        delete("/system/management/server-types/"+serverTypeId+"/measurements", params);
    }

    @Test
    public void deleteServerTypeMeasurement() throws Exception {
        Integer serverTypeId = 2;
        Integer measurementId = 49;

        delete("/system/management/server-types/"+serverTypeId+"/measurements/"+measurementId+"");
    }

    @Test
    public void getServerTypeMeasuremenMetrics() throws  Exception {

        Integer serverTypeId = 8;

        get("/system/management/server-types/"+serverTypeId+"/critical");
    }

    /*@Test
    public void pagingCritical() throws  Exception {
        Integer serverTypeId = 8;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/server-types/"+serverTypeId+"/critical", params);
    }*/

    @Test
    public void getServerTypeCritical() throws  Exception {

        Integer serverTypeId = 2;

        get("/system/management/server-types/"+serverTypeId+"/critical");
    }

    /*@Test
    public void getServerTypeMeasurementMetrics() throws Exception {

        Integer serverTypeId = 2;
        Integer measurementId = 1;

        get("/system/management/server-types/"+serverTypeId+"/measurements/"+measurementId+"/metrics");
    }*/

    @Test
    public void getServerTypeMeasurementMetrics() throws Exception {

        Integer serverTypeId = 2;
        Integer measurementId = 1;

        get("/system/management/server-types/"+serverTypeId+"/measurements/"+measurementId+"/metrics");
    }

    @Test
    public void updateCritical() throws Exception {
        Integer serverTypeId = 8;
        Integer measurementId =  6;
        Integer metricId = 27;

        ServerTypeCriticalValue update = new ServerTypeCriticalValue();
        update.setWarning(65.42);
        update.setCritical(80.01);

        put("/system/management/server-types/"+serverTypeId+"/measurements/"+measurementId+"/metrics/"+metricId+"", update);
    }

    @Test  //다중 메트릭 추가
    public void insertServerTypeMeasurementMetrics() throws Exception {
        Integer serverTypeId = 2;
        Integer measurementId = 2;
        Integer[] metricIds = {9, 10, 11};

        post("/system/management/server-types/"+serverTypeId+"/measurements/"+measurementId+"", metricIds);
    }

    @Test
    public void deleteServerTypeMeasurementsMetrics() throws Exception {
        Integer serverTypeId = 2;
        Integer measurementId = 2;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("metricIds", "10,11");

        delete("/system/management/server-types/"+serverTypeId+"/measurements/"+measurementId+"/metrics", params);
    }

    @Test
    public void deleteServerTypeMeasurementsMetric() throws Exception {
        Integer serverTypeId = 2;
        Integer measurementId = 2;
        Integer metricId = 9;

        delete("/system/management/server-types/"+serverTypeId+"/measurements/"+measurementId+"/metrics/"+metricId+"");
    }
}
