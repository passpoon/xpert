package com.crossent.monitoring.portal.mongroup.monitoring;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonServerTest extends AbstractMockTest {

    @Test
    public void pageServerStatuses() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));


        Integer monitoringGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/server/server-statuses", params);
    }


    @Test
    public void getServerDetailStatus() throws Exception {

        SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);
        searchReqVo.setRangeType("20m");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

       // params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));

        Integer monitoringGroupId = 1;
        Integer serverResourceId = 19;

        ///monitoring-groups/{monitoringGroupId}/monitoring/server/server-statuses/{serverResourceId}

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/server/server-statuses/"+serverResourceId, params);
    }
}
