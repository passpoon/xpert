package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_app_group_critical_value", schema = "mondb", catalog = "")
@IdClass(MgAppGroupCriticalValuePK.class)
public class MgAppGroupCriticalValue implements Serializable, CriticalValueInterface {
    private Integer mgAppGroupId;
    private Integer metricId;
    private Double critical;
    private Double warning;

    @JsonIgnore
    private MgAppGroup mgAppGroup;

    private Metric metric;

    @Id
    @Column(name = "mg_app_group_id", nullable = false)
    public Integer getMgAppGroupId() {
        return mgAppGroupId;
    }

    public void setMgAppGroupId(Integer mgAppGroupId) {
        this.mgAppGroupId = mgAppGroupId;
    }

    @Id
    @Column(name = "metric_id", nullable = false)
    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    @Basic
    @Column(name = "critical", nullable = true, precision = 0)
    public Double getCritical() {
        return critical;
    }

    public void setCritical(Double critical) {
        this.critical = critical;
    }

    @Basic
    @Column(name = "warning", nullable = true, precision = 0)
    public Double getWarning() {
        return warning;
    }

    public void setWarning(Double warning) {
        this.warning = warning;
    }

    //추가
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="mg_app_group_id", referencedColumnName = "id", insertable = false, updatable = false)
    })
    public MgAppGroup getMgAppGroup() { return mgAppGroup; }

    public void setMgAppGroup(MgAppGroup mgAppGroup) { this.mgAppGroup = mgAppGroup; }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="metric_id", referencedColumnName = "id", insertable = false, updatable = false)
    })
    public Metric getMetric() { return metric; }

    public void setMetric(Metric metric) { this.metric = metric; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAppGroupCriticalValue{");
        sb.append("mgAppGroupId=").append(mgAppGroupId);
        sb.append(", metricId=").append(metricId);
        sb.append(", critical=").append(critical);
        sb.append(", warning=").append(warning);
        sb.append('}');
        return sb.toString();
    }
}
