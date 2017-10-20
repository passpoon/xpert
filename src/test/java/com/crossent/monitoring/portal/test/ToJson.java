package com.crossent.monitoring.portal.test;

import com.crossent.monitoring.portal.common.vo.PagingReqVo;
import com.crossent.monitoring.portal.common.vo.PagingResVo;
import com.crossent.monitoring.portal.common.vo.SearchReqVo;
import com.crossent.monitoring.portal.common.lib.json.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToJson {


    public static void main(String[] args){
        try {
            ObjectMapper om = ObjectMapperFactory.newInstance();


            //PagingReqVo
            PagingReqVo req = new PagingReqVo();

            //PagingResVo
            PagingResVo res = new PagingResVo();

            //SearchReqVo
            SearchReqVo search = new SearchReqVo();
            search.addKeyword("aaa", "11111");
            search.addKeyword("bbb", "2222");
            search.addKeyword("ccc", "3333");


            String jsonReq = om.writer().withDefaultPrettyPrinter().writeValueAsString(req);
            System.out.println("jsonReq :" + jsonReq);
            String jsonRes = om.writer().withDefaultPrettyPrinter().writeValueAsString(res);
            System.out.println("jsonRes : " + jsonRes);
            String jsonSearch = om.writer().withDefaultPrettyPrinter().writeValueAsString(search);
            System.out.println("jsonSearch : " + jsonSearch);




        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}

