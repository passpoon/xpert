package com.crossent.monitoring.portal.jpa.domain;

public interface CriticalValueInterface {

    public Metric getMetric();
    public void setMetric(Metric metric);
    public Double getCritical();
    public void setCritical(Double critical);
    public Double getWarning();
    public void setWarning(Double warning);
}
