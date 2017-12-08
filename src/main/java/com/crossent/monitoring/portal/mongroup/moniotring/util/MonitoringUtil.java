package com.crossent.monitoring.portal.mongroup.moniotring.util;

import com.crossent.monitoring.portal.common.vo.CriticalValueMapVo;
import com.crossent.monitoring.portal.jpa.domain.CriticalValueInterface;

import java.text.DecimalFormat;
import java.util.*;

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

    public static Map<Integer, CriticalValueMapVo> convertCriticalValToMeasurementMap(List<CriticalValueInterface> criticalValueInterfaces){
       // Map<Integer, List<CriticalValueMapVo>> measurementMap = new HashMap<Integer, List<CriticalValueMapVo>>();


        Map<Integer, List<CriticalValueInterface>> measurementMap = new HashMap<Integer, List<CriticalValueInterface>>();

        for(CriticalValueInterface criticalValueInterface : criticalValueInterfaces){
            Integer mId = criticalValueInterface.getMetric().getMeasurementId();

            List<CriticalValueInterface> criticalValueInterfaceList = measurementMap.get(mId);
            if(criticalValueInterfaceList == null){
                criticalValueInterfaceList = new ArrayList<CriticalValueInterface>();
                measurementMap.put(mId, criticalValueInterfaceList);
            }

            criticalValueInterfaceList.add(criticalValueInterface);

        }


        Map<Integer, CriticalValueMapVo> criticalValueMapListMap = new HashMap<Integer, CriticalValueMapVo>();


        Iterator<Integer> keys = measurementMap.keySet().iterator();
        while(keys.hasNext()){
            Integer key = keys.next();
            //List<CriticalValueInterface> valueInterfaces = criticalCollectionToInterface(measurementMap.get(key));
            CriticalValueMapVo criticalValueMapVo = new CriticalValueMapVo(measurementMap.get(key));
            criticalValueMapListMap.put(key, criticalValueMapVo);
        }
        return criticalValueMapListMap;
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
