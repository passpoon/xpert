package com.crossent.monitoring.portal.test;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;

public class TestElasticsearchQuery {

    public static void main(String[] args){


        BoolQueryBuilder query = QueryBuilders.boolQuery();
        BoolQueryBuilder filters = QueryBuilders.boolQuery();


        query.filter(filters);


        filters.must(QueryBuilders.termsQuery("host", "monagtd01", "monagtd02"));
        query.must(QueryBuilders.matchQuery("message", "parts snapmgr"));
       // QueryBuilders.matchAllQuery()


//        query.f
//
//        //BoolQueryBuilder hosts = QueryBuilders.boolQuery();
//        //query.must(hosts);
//
//
////        hosts.should(QueryBuilders.termQuery("host", "monagtd01"));
////        hosts.should(QueryBuilders.termQuery("host", "monagtd02"));
//        //query.should(QueryBuilders.termQuery("host", "monagtd02"));
////        query.must(QueryBuilders.termQuery("host", "monagtd01"));
//        //query.must(QueryBuilders.termQuery("host", "monagtd02"));
//       // query.must(QueryBuilders.termQuery("host", "monagtd02"));
//        query.must(QueryBuilders.termsQuery("host", "monagt01"));
//
//
//
//        query.must(QueryBuilders.matchQuery("message", "port"));




        //QueryBuilders.fil

       // FilteredQueryBuilder




        System.out.println(query);



    }
}
