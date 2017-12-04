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
