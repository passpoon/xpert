package com.crossent.monitoring.portal.sample.service;


import com.crossent.monitoring.portal.common.lib.elasticsearch.ElasticsearchTemplate;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.influxdb.dto.QueryResult.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TestService {

    private static Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void testInfluxdb(){
        Query query = new Query("select * from cpu where time > now() - 5m; select * from mem where time > now() - 5m", influxDBTemplate.getDatabase());

        QueryResult qr = influxDBTemplate.query(query);
        List<Result> results = qr.getResults();

        for(Result result : results){
            List<Series> serieses = result.getSeries();
            for(Series series : serieses){
                logger.debug("series name : {}", series.getName());

                List<String> columns = series.getColumns();
                logger.debug("columns : {}" , columns);

                List<List<Object>> values = series.getValues();
                logger.debug("values : {}" , values);

            }
        }

    }


    public void testElasticSearch(){

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("@timestamp").from("2017-10-31T00:00:00").to("2017-10-31T23:00:00").timeZone("Asia/Seoul").format("strict_date_optional_time");

        SearchResponse searchResponse = elasticsearchTemplate.query("log-*", "filebeat-log", rangeQueryBuilder);

        //Set<String> result = new HashSet<String>();

        for (SearchHit hit : searchResponse.getHits()) {
            logger.debug("getIndex :{}", hit.getIndex());
            logger.debug("toString :{}", hit.getSource());
            logger.debug("getId :{} : {} : {}", hit.getId(), hit.getSource().get("@timestamp"), hit.getSource().get("message"));

        }
    }





}
