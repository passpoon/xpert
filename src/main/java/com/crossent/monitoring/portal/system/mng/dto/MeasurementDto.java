package com.crossent.monitoring.portal.system.mng.dto;

import java.io.Serializable;
import java.util.List;


public class MeasurementDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private List<MetricDto> metrics;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MetricDto> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricDto> metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MeasurementDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", metrics=").append(metrics);
        sb.append('}');
        return sb.toString();
    }
}
