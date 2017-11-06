package com.crossent.monitoring.portal.system.mng.dto;


import java.io.Serializable;


public class MetricDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private String metricTypeCode;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMetricTypeCode() {
        return metricTypeCode;
    }

    public void setMetricTypeCode(String metricTypeCode) {
        this.metricTypeCode = metricTypeCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetricDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", metricTypeCode='").append(metricTypeCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
