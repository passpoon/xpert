package com.crossent.monitoring.portal.mongroup.monitoring;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonAppGroupTest extends AbstractMockTest {

    @Test
    public void pageAppGroupStatuses() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));


        Integer monitoringGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app-group/app-group-statuses", params);
    }


    @Test
    public void pageAppGroupAppStatuses() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        //SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        //params.add("search", JsonUtil.ObjectToJson(searchReqVo));


        Integer monitoringGroupId = 1;
        Integer appGroupId = 2;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app-group/app-group-statuses/"+appGroupId+"/app-statuses", params);
    }


    @Test
    public void pageEvent() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));


        Integer monitoringGroupId = 1;
        Integer appGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app-group/app-group-statuses/"+appGroupId+"/events", params);
    }


    @Test
    public void pageLog() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));


        Integer monitoringGroupId = 1;
        Integer appGroupId = 19;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/app-group/app-group-statuses/"+appGroupId+"/logs", params);
    }



}
