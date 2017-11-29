package com.crossent.monitoring.portal.mongroup.moniotring.dao;

import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.CriticalValueMapDto;
import com.crossent.monitoring.portal.mongroup.moniotring.util.MonitoringUtil;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MonServerDao {

    private static Logger logger = LoggerFactory.getLogger(MonServerDao.class);

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public Map<String, Object> selectReferenceValue(String measurementName, String hostName, CriticalValueMapDto criticalValueMap){

        Map<String, Object> resMap = resMap =  new HashMap<String, Object>();

        String sql = MonitoringUtil.makeInfluxCriticalQuery(measurementName, hostName, criticalValueMap);

        logger.debug("sql : {}", sql);
        Query query = new Query(sql, influxDBTemplate.getDatabase());

        QueryResult qr = influxDBTemplate.query(query);
        List<QueryResult.Result> results = qr.getResults();

        for(QueryResult.Result result : results){
            List<QueryResult.Series> serieses = result.getSeries();
            if(serieses == null){
                return null;
            }
            for(QueryResult.Series series : serieses){
                logger.debug("series name : {}", series.getName());
                if(measurementName.equals(series.getName())){
                    List<String> columns = series.getColumns();
                    List<List<Object>> values = series.getValues();
                    if(values.size() >= 0){
                        List<Object> row = values.get(0);
                        for( int i=0; i < columns.size(); i++){
                            String columnName = columns.get(i);
                            Object val = row.get(i);
                            resMap.put(columnName, val);
                        }
                    }

                }
            }
        }

        return resMap;
    }


    public Map<String, Object> selectProcessInfoForServer(String hostName){
        Map<String, Object> resMap = resMap =  new HashMap<String, Object>();

        String sql = MonitoringUtil.makeInfluxProcessQueryForServer(hostName);

        logger.debug("sql : {}", sql);
        Query query = new Query(sql, influxDBTemplate.getDatabase());


        QueryResult qr = influxDBTemplate.query(query);
        List<QueryResult.Result> results = qr.getResults();

        for(QueryResult.Result result : results){
            List<QueryResult.Series> serieses = result.getSeries();
            if(serieses == null){
                return null;
            }
            for(QueryResult.Series series : serieses){
                logger.debug("series name : {}", series.getName());
                if(ApplicationProperties.influxMeasurementProcess.equals(series.getName())){
                    List<String> columns = series.getColumns();
                    List<List<Object>> values = series.getValues();
                    if(values.size() >= 0){
                        List<Object> row = values.get(0);
                        for( int i=0; i < columns.size(); i++){
                            String columnName = columns.get(i);
                            Object val = row.get(i);
                            resMap.put(columnName, val);
                        }
                    }else{
                        return null;
                    }

                }
            }
        }
        return resMap;
    }

}
