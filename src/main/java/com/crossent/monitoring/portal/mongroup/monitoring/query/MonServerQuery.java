package com.crossent.monitoring.portal.mongroup.monitoring.query;

import com.crossent.monitoring.portal.common.constants.MetricFuncType;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.jpa.domain.Metric;
import com.crossent.monitoring.portal.common.vo.CriticalValueMapVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MonServerQuery {

    public static String makeInfluxCriticalQuery(String measurementName, String hostName, CriticalValueMapVo criticalValueMap){

        StringBuilder queryBuffer = new StringBuilder("SELECT ");
        int idx = 0;
        List<String> metricNames = criticalValueMap.getMetricNames();
        for (String metricName : metricNames) {
            if(idx > 0){
                queryBuffer.append(",");
            }

            String funcTypeCode = criticalValueMap.getFuncTypeCode(metricName);
            queryBuffer.append(metricToFunction(metricName, funcTypeCode));
//            switch (funcTypeCode) {
//                case (Constants.FUNC_TYPE_AVERAGE_HIGH):
//                case (Constants.FUNC_TYPE_AVERAGE_LOW):
//                    queryBuffer.append(String.format("MEAN(%s) as %s ", metricName, metricName));
//                    break;
//                case (Constants.FUNC_TYPE_IO_USAGE):
//                    queryBuffer.append(String.format("LAST(%s) - FIRST(%s) as %s ", metricName, metricName, metricName));
//                    break;
//                case (Constants.FUNC_TYPE_VOLUME_HIGH):
//                case (Constants.FUNC_TYPE_VOLUME_LOW):
//                    queryBuffer.append(String.format("LAST(%s) as %s ", metricName, metricName));
//                    break;
//                default:
//                    throw new BusinessException(MessageUtil.getMessage("unDefCode", funcTypeCode));
//            }
            idx++;
        }
        queryBuffer.append(String.format("FROM %s ", measurementName));

        String groupingTerm = ApplicationProperties.influxQeryGroupTerm;

        queryBuffer.append(String.format("WHERE time > NOW() - %s ", groupingTerm));
        queryBuffer.append(String.format("AND host='%s' ",hostName));

        //Map<String, String> filterMap  = influxQueryFilters.get(mName);
        Map<String, String> filter = ApplicationProperties.influxQueryFilters.get(measurementName);

        if( filter != null){
            for( Map.Entry<String, String> entrySet : filter.entrySet()){
                queryBuffer.append(String.format("AND %s='%s' ",entrySet.getKey(), entrySet.getValue()));
            }
        }

        queryBuffer.append(";");
        return queryBuffer.toString();
    }


    public static String makeInfluxProcessQueryForServer(String hostName){

        return String.format("select total, (sleeping + running + paging + stopped) as normal, (blocked + dead + zombies + unknown) as abnormal, sleeping, running, paging, stopped, blocked, dead, zombies,unknown from  %s where time > now() - %s and host = '%s' order by time desc limit 1", ApplicationProperties.influxMeasurementProcess, ApplicationProperties.influxQeryGroupTerm, hostName);
    }


    public static String makeMetricListQuery(String hostName, String measurementName, String term, Collection<Metric> metrics){
        Map<String, String> filter = ApplicationProperties.influxQueryFilters.get(measurementName);
        StringBuilder queryBuffer = new StringBuilder("");
        queryBuffer.append("SELECT ");
        int idx = 0;
        for(Metric metric : metrics){
            if(idx > 0){
                queryBuffer.append(",");
            }
            String metricName = metric.getName();
            String funcTypeCode = metric.getFuncTypeCode();
            queryBuffer.append(metricToFunction(metricName, funcTypeCode));
            idx++;
        }

        queryBuffer.append(String.format("FROM %s ", measurementName));

        String groupingTerm = ApplicationProperties.influxQeryGroupTerm;

        queryBuffer.append(String.format("WHERE time > NOW() - %s ", term));
        queryBuffer.append(String.format("AND host='%s' ",hostName));
        if( filter != null){
            for( Map.Entry<String, String> entrySet : filter.entrySet()){
                queryBuffer.append(String.format("AND %s='%s' ",entrySet.getKey(), entrySet.getValue()));
            }
        }

        queryBuffer.append(String.format("GROUP BY time(%s) ", groupingTerm));
        queryBuffer.append(String.format("ORDER BY time desc;"));

        return queryBuffer.toString();
    }


    private static String metricToFunction(String metricName, String funcTypeCode){


        MetricFuncType funcType = MetricFuncType.forCode(funcTypeCode);
        if(funcType == null){
            throw new BusinessException("unDefMetricFunctionCode", metricName);
        }
        switch (funcType) {
            case AVERAGE_HIGH:
            case AVERAGE_LOW:
                return String.format("MEAN(%s) as %s ", metricName, metricName);
            case IO_USAGE:
                return String.format("LAST(%s) - FIRST(%s) as %s ", metricName, metricName, metricName);
            case VOLUME_HIGH:
            case VOLUME_LOW:
                return String.format("LAST(%s) as %s ", metricName, metricName);
            default:
                throw new BusinessException("unDefCode", funcTypeCode);
        }

    }


}
