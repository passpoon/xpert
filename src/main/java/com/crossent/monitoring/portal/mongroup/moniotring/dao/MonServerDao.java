package com.crossent.monitoring.portal.mongroup.moniotring.dao;

import com.crossent.monitoring.portal.common.lib.util.DateUtil;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.common.vo.CriticalValueMapVo;
import com.crossent.monitoring.portal.mongroup.moniotring.query.MonServerQuery;
import com.crossent.monitoring.portal.mongroup.moniotring.util.MonitoringUtil;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MonServerDao {

    private static Logger logger = LoggerFactory.getLogger(MonServerDao.class);

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public Map<String, Object> selectReferenceValue(String measurementName, String hostName, CriticalValueMapVo criticalValueMap){

        Map<String, Object> resMap = resMap =  new HashMap<String, Object>();

        String sql = MonServerQuery.makeInfluxCriticalQuery(measurementName, hostName, criticalValueMap);

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

        String sql = MonServerQuery.makeInfluxProcessQueryForServer(hostName);

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


    public List<Map<String, String>> listMetrics(String hostName, String measurementName, String searchType, Collection<Metric> metrics){

        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();

        String sql = MonServerQuery.makeMetricListQuery(hostName, measurementName, searchType, metrics);
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
                    if(series == null){
                        return resList;
                    }
                    List<String> columns = series.getColumns();
                    List<List<Object>> values = series.getValues();

                    for(List<Object> row : values){
                        //List<Object> row = values.get(0);
                        Map<String, String> rowMap = new HashMap<String, String>();
                        for( int i=0; i < columns.size(); i++){
                            String columnName = columns.get(i);
                            Object val = row.get(i);
                            if("time".equals(columnName)){

                                try {
                                    rowMap.put(columnName , DateUtil.utcTimestampToString(val.toString(), DateUtil.DATE_TIME_PATTERN));
                                } catch (Exception e) {
                                    rowMap.put(columnName , (String)val);
                                }
                            }else{

                                if(val == null){
                                    rowMap.put(columnName, "0");
                                }else {
                                    rowMap.put(columnName, MonitoringUtil.round2ToString(MonitoringUtil.toDouble(val)));
                                }
                            }
                        }
                        resList.add(rowMap);
                    }

                }
            }
        }
        return resList;

    }

}
