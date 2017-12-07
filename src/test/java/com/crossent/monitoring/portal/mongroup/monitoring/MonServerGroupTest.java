package com.crossent.monitoring.portal.mongroup.monitoring;

import com.crossent.monitoring.portal.common.lib.util.JsonUtil;
import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

public class MonServerGroupTest extends AbstractMockTest {

    @Test
    public void pageServerGroupStatuses() throws Exception {

        PagingReqVo pagingReqVo = new PagingReqVo();
        pagingReqVo.setPage(0);
        pagingReqVo.setPageSize(10);

        SearchReqVo searchReqVo = new SearchReqVo();
        //searchReqVo.addKeyword(null, null);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("paging", JsonUtil.ObjectToJson(pagingReqVo));
        params.add("search", JsonUtil.ObjectToJson(searchReqVo));


        Integer monitoringGroupId = 1;

        get("/monitoring-groups/"+monitoringGroupId+"/monitoring/server-group/server-statuses", params);
    }



}
