package com.crossent.monitoring.portal.mongroup.moniotring.dto;

import com.crossent.monitoring.portal.common.constants.Constants;
import com.crossent.monitoring.portal.common.exception.BusinessException;
import com.crossent.monitoring.portal.common.lib.util.MessageUtil;
import com.crossent.monitoring.portal.jpa.domain.CriticalValueInterface;
import com.crossent.monitoring.portal.jpa.domain.Metric;

import java.util.*;

public class CriticalValueMapDto {


    private Map<String, CriticalValueInterface> criticalValueInterfaceMap;

    public CriticalValueMapDto(List<CriticalValueInterface> criticalValueInterfaces) {
        criticalValueInterfaceMap = new HashMap<String, CriticalValueInterface>();

        for (CriticalValueInterface cvInterface : criticalValueInterfaces) {
            Metric metric = cvInterface.getMetric();

            metric.getName();

            criticalValueInterfaceMap.put(metric.getName(), cvInterface);
        }

    }


    public Double getWarningVal(String metricName) {
        return criticalValueInterfaceMap.get(metricName).getWarning();
    }

    public Double getCriticalVal(String metricName) {
        return criticalValueInterfaceMap.get(metricName).getCritical();
    }


    public boolean isWarning(String metricName, Double val) {
        CriticalValueInterface cv = criticalValueInterfaceMap.get(metricName);
        Metric metric = cv.getMetric();

        String funcTypeCode = metric.getFuncTypeCode();

        switch (funcTypeCode) {
            case Constants.FUNC_TYPE_AVERAGE_HIGH:
            case Constants.FUNC_TYPE_IO_USAGE:
            case Constants.FUNC_TYPE_VOLUME_HIGH: {
                return val >= cv.getWarning();
            }

            case Constants.FUNC_TYPE_AVERAGE_LOW:
            case Constants.FUNC_TYPE_VOLUME_LOW: {
                return val <= cv.getWarning();
            }
            default:
                throw new BusinessException(MessageUtil.getMessage("unDefMetricFuncCode", funcTypeCode));
        }

    }


    public boolean isCritical(String metricName, Double val) {
        CriticalValueInterface cv = criticalValueInterfaceMap.get(metricName);
        Metric metric = cv.getMetric();

        String funcTypeCode = metric.getFuncTypeCode();

        switch (funcTypeCode) {
            case Constants.FUNC_TYPE_AVERAGE_HIGH:
            case Constants.FUNC_TYPE_IO_USAGE:
            case Constants.FUNC_TYPE_VOLUME_HIGH: {
                return val >= cv.getCritical();
            }

            case Constants.FUNC_TYPE_AVERAGE_LOW:
            case Constants.FUNC_TYPE_VOLUME_LOW: {
                return val <= cv.getCritical();
            }
            default:
                throw new BusinessException(MessageUtil.getMessage("unDefMetricFuncCode", funcTypeCode));
        }
    }


    public List<String> getMetricNames() {
        List<String> metricNames = new ArrayList<String>();
        Iterator<String> metrics = criticalValueInterfaceMap.keySet().iterator();
        while (metrics.hasNext()) {
            metricNames.add(metrics.next());
        }
        return metricNames;
    }


    public Map<String, CriticalValueInterface> getCriticalValueInterfaceMap() {
        return criticalValueInterfaceMap;
    }

    public void setCriticalValueInterfaceMap(Map<String, CriticalValueInterface> criticalValueInterfaceMap) {
        this.criticalValueInterfaceMap = criticalValueInterfaceMap;
    }


    public String getFuncTypeCode(String metricName){
        return criticalValueInterfaceMap.get(metricName).getMetric().getFuncTypeCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CriticalValueMapDto{");
        sb.append("criticalValueInterfaceMap=").append(criticalValueInterfaceMap);
        sb.append('}');
        return sb.toString();
    }
}
