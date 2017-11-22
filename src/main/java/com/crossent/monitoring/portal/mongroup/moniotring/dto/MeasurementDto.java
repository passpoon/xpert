package com.crossent.monitoring.portal.mongroup.moniotring.dto;

import java.io.Serializable;
import java.util.List;

public class MeasurementDto implements Serializable {

    private List<MetricDto> metrics;
    private String status;
    private String message;

    public List<MetricDto> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricDto> metrics) {
        this.metrics = metrics;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
