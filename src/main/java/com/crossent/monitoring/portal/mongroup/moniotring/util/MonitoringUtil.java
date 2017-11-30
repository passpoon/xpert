package com.crossent.monitoring.portal.mongroup.moniotring.util;

import com.crossent.monitoring.portal.common.constants.Constants;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.common.properties.ApplicationProperties;
import com.crossent.monitoring.portal.jpa.domain.CriticalValueInterface;
import com.crossent.monitoring.portal.mongroup.moniotring.dto.CriticalValueMapDto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MonitoringUtil {
    private static DecimalFormat df = new DecimalFormat("#.#");

    public static List<CriticalValueInterface> criticalCollectionToInterface(Collection<?> objects){

        List<CriticalValueInterface> list = new ArrayList<CriticalValueInterface>();


        for(Object object : objects){
            if(object instanceof CriticalValueInterface){
                list.add((CriticalValueInterface)object);

            }
        }
        return list;
    }

    public static String makeInfluxCriticalQuery(String measurementName, String hostName, CriticalValueMapDto criticalValueMap){

        StringBuilder queryBuffer = new StringBuilder("SELECT ");
        int idx = 0;
        List<String> metricNames = criticalValueMap.getMetricNames();
        for (String metricName : metricNames) {
            if(idx > 0){
                queryBuffer.append(",");
            }

            String funcTypeCode = criticalValueMap.getFuncTypeCode(metricName);
            //logger.debug("metric FunTypeCode ::{} ", metric.getFuncTypeCode());
            switch (funcTypeCode) {
                case (Constants.FUNC_TYPE_AVERAGE_HIGH):
                case (Constants.FUNC_TYPE_AVERAGE_LOW):
                    queryBuffer.append(String.format("MEAN(%s) as %s ", metricName, metricName));
                    break;
                case (Constants.FUNC_TYPE_IO_USAGE):
                    queryBuffer.append(String.format("LAST(%s) - FIRST(%s) as %s ", metricName, metricName, metricName));
                    break;
                case (Constants.FUNC_TYPE_VOLUME_HIGH):
                case (Constants.FUNC_TYPE_VOLUME_LOW):
                    queryBuffer.append(String.format("LAST(%s) as %s ", metricName, metricName));
                    break;
                default:
                    throw new BusinessException(MessageUtil.getMessage("unDefCode", funcTypeCode));
            }
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

        queryBuffer.append("ORDER BY time desc;");
        return queryBuffer.toString();
    }


    public static String makeInfluxProcessQueryForServer(String hostName){

        return String.format("select total, (sleeping + running + paging + stopped) as normal, (blocked + dead + zombies + unknown) as abnormal, sleeping, running, paging, stopped, blocked, dead, zombies,unknown from  %s where time > now() - %s and host = '%s' order by time desc limit 1", ApplicationProperties.influxMeasurementProcess, ApplicationProperties.influxQeryGroupTerm, hostName);
    }


    public static Double toDouble(Object val){
        if(val == null){
            return 0D;
        }

        if(val instanceof Double){
            return (Double)val;
        }else if(val instanceof Float){
            return new Double((Float)val);
        }else if(val instanceof Long){
            return new Double((Long)val);
        }else if(val instanceof Integer){
            return new Double((Integer)val);
        }else{
            return new Double(val.toString());
        }
    }


    public static Double round2(Double val){
        return Math.round(val * 100d)/100d;
    }

    public static Long round(Double val){
        return Math.round(val);
    }

    public static String round2ToString(Double val){
        return df.format(Math.round(val * 100d)/100d);
    }


    public static String roundToString(Double val){
        return df.format(Math.round(val));
    }
}
