package com.crossent.monitoring.portal.system.mng.test;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.jpa.domain.AppInfo;
import com.crossent.monitoring.portal.jpa.domain.Measurement;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

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
        appInfo.setName("test3");
        appInfo.setDescription("test Description3");

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

        get("/system/management/app-infos/"+appInfoId+"/mesurements");
    }

    @Test
    public void insertAppInfoMeasurement() throws Exception {
        Integer appInfoId = 3;
        Measurement measurement = new Measurement();
        measurement.setId(48);

        post("/system/management/app-infos/"+appInfoId+"/mesurements", measurement);
    }

    @Test
    public void deleteAppInfoMeasurements() throws Exception {
        Integer appInfoId = 3;

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("measurementIds", "47,48");

        delete("/system/management/app-infos/"+appInfoId+"/mesurements", params);
    }

    @Test
    public void deleteAppInfoMeasurement() throws Exception {
        Integer appInfoId = 2;
        Integer measurementId = 48;

        delete("/system/management/app-infos/"+appInfoId+"/mesurements/"+measurementId+"");
    }
}
