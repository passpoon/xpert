package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.jpa.domain.ServerType;
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
        serverType.setName("bbb");
        serverType.setDescription("cc");

        List<MeasurementDto> measurementDtoList = new ArrayList<MeasurementDto>();

        MeasurementDto measurementDto = new MeasurementDto();
        measurementDto.setId(1);
        measurementDtoList.add(measurementDto);

        measurementDto = new MeasurementDto();
        measurementDto.setId(2);
        measurementDtoList.add(measurementDto);

        serverType.setMeasurements(measurementDtoList);

        post("/system/management/server-types", serverType);
    }

    @Test   // LinkedMultiValueMap String, Integer 추가
    public void deleteServerTypes() throws  Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("serverTypeIds", "5,7");

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

        Integer serverTypeId = 4;

        delete("/system/management/server-types/" + serverTypeId);
    }

    @Test
    public void getServerTypeMeasurement() throws Exception {

        Integer serverTypeId = 2;

        get("/system/management/server-types/"+serverTypeId+"/mesurements");
    }

    @Test
    public void insertServerTypeMeasurement() throws Exception {

        Integer serverTypeId = 2;
        Measurement measurement = new Measurement();
        measurement.setId(47);

        post("/system/management/server-types/"+serverTypeId+"/mesurements", measurement);
    }

    @Test
    public void deleteServerTypeMeasurements() throws Exception {
        Integer serverTypeId = 2;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("measurementIds", "47,48");

        delete("/system/management/server-types/"+serverTypeId+"/mesurements", params);
    }

    @Test
    public void deleteServerTypeMeasurement() throws Exception {
        Integer serverTypeId = 2;
        Integer measurementId = 49;

        delete("/system/management/server-types/"+serverTypeId+"/mesurements/"+measurementId+"");
    }



}
