package com.crossent.monitoring.portal.mongroup.monitoring;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonApplicationTest extends AbstractMockTest {

    @Test
    public void pageApplicationStatuses() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));


        Integer monitoringGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app/app-statuses", params);
    }

    @Test
    public void getAppDetailStatus() throws Exception {

        SearchReqVo searchReqVo = new SearchReqVo();
        searchReqVo.setRangeType("5m");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        Integer monitoringGroupId = 1;
        Integer appResourceId = 18;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app/app-statuses/"+appResourceId, params);
    }

    @Test
    public void pageEvent() throws Exception{

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(100);

        SearchReqVo searchReqVo = new SearchReqVo();

        //searchReqVo.setKeywords();


        //searchReqVo.addKeyword("RESOURCE-TYPE", "SERVER");
        //searchReqVo.addKeyword("RESOURCE-TYPE", "LOG");

//        searchReqVo.addKeyword("STATE", "NORMAL");
//        searchReqVo.addKeyword("STATE", "WARNNING");
        //searchReqVo.addKeyword("STATE", "CRITICAL");
//        searchReqVo.addKeyword("STATE", "START");
//        searchReqVo.addKeyword("STATE", "STOP");
//        searchReqVo.addKeyword("STATE", "DEBUG");
//        searchReqVo.addKeyword("STATE", "INFO");
//        searchReqVo.addKeyword("STATE", "WARN");
//        searchReqVo.addKeyword("STATE", "ERROR");

        //searchReqVo.setRangeType("1h");
        searchReqVo.setRangeType("1d");
//        searchReqVo.setRangeStart("20171202011000");
//        searchReqVo.setRangeEnd("20171202022000");



        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        Integer monitoringGroupId = 1;
        Integer appResourceId = 19;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app/app-statuses/"+appResourceId+"/events", params);

    }

    @Test
    public void pageLog() throws Exception{

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(1);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
//        searchReqVo.setRangeType("manual");
//        searchReqVo.setRangeStart("20171202011000");
//        searchReqVo.setRangeEnd("20171202022000");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        Integer monitoringGroupId = 1;
        Integer appResourceId = 19;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app/app-statuses/"+appResourceId+"/logs", params);
    }
}
