package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.AppInfoCriticalValue;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import com.crossent.monitoring.portal.system.mng.dto.AppInfoDto;
import com.crossent.monitoring.portal.system.mng.dto.MeasurementDto;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class AppInfoTest extends AbstractMockTest {

    @Test
    public void pageAppInfo() throws Exception {
        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.addKeyword("name", "my");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/app-infos", params);
    }

    @Test
    public void insertAppInfo() throws  Exception {

        AppInfo appInfo = new AppInfo();
        appInfo.setName("test4");
        appInfo.setDescription("test Description4");

       /* List<MeasurementDto> measurementDtoList = new ArrayList<MeasurementDto>();

        MeasurementDto measurementDto = new MeasurementDto();
        measurementDto.setId(1);
        measurementDtoList.add(measurementDto);

        measurementDto = new MeasurementDto();
        measurementDto.setId(2);
        measurementDtoList.add(measurementDto);

        appInfo.setMeasurements(measurementDtoList);*/
        post("/system/management/app-infos", appInfo);
    }

    @Test
    public void deleteAppInfos() throws Exception {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("appInfoIds", "5,6");

        delete("/system/management/app-infos", params);
    }

    @Test
    public void getAppInfo() throws Exception {
        Integer appInfoId = 1;

        get("/system/management/app-infos/" + appInfoId);
    }

    @Test
    public void updateAppInfo() throws Exception {
        Integer appInfoId = 8;

        AppInfo dto = new AppInfo();
        dto.setId(appInfoId);
        dto.setName("test");
        dto.setDescription("update test");

        put("/system/management/app-infos/"+ appInfoId, dto);
    }

    @Test
    public void deleteAppInfo() throws Exception {
        Integer appInfoId = 8;

        delete("/system/management/app-infos/" + appInfoId);
    }

    @Test
    public void getAppInfoMeasurements() throws Exception {
        Integer appInfoId = 7;

        get("/system/management/app-infos/"+appInfoId+"/measurements");
    }

    @Test
    public void insertAppInfoMeasurement() throws Exception {
        Integer appInfoId = 13;
        Integer[] measurementIds = {47, 48};

        post("/system/management/app-infos/"+appInfoId+"/measurements", measurementIds);
    }

    @Test
    public void deleteAppInfoMeasurements() throws Exception {
        Integer appInfoId = 3;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("measurementIds", "47,48");

        delete("/system/management/app-infos/"+appInfoId+"/measurements", params);
    }

    @Test
    public void deleteAppInfoMeasurement() throws Exception {
        Integer appInfoId = 2;
        Integer measurementId = 48;

        delete("/system/management/app-infos/"+appInfoId+"/measurements/"+measurementId+"");
    }

    @Test
    public void pagingAppInfoCritical() throws  Exception {
        Integer appInfoId = 16;

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        get("/system/management/app-infos/"+appInfoId+"/critical", params);
    }

    @Test
    public void updateCritical() throws Exception {
        Integer appInfoId = 16;
        Integer measurementId = 66;
        Integer metricId = 91;

        AppInfoCriticalValue update = new AppInfoCriticalValue();
        update.setWarning(65.42);
        update.setCritical(80.01);

        put("/system/management/app-infos/"+appInfoId+"/measurements/"+measurementId+"/metrics/"+metricId+"", update);
    }
}
