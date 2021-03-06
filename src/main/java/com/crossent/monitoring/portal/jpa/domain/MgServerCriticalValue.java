package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_server_critical_value", schema = "mondb")
@IdClass(MgServerCriticalValuePK.class)
public class MgServerCriticalValue implements Serializable, CriticalValueInterface {
    private Integer monGroupId;
    private Integer serverResourceId;
    private Integer metricId;
    private Double critical;
    private Double warning;

    @JsonIgnore
    private MgServer mgServer;

    private Metric metric;

    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Id
    @Column(name = "server_resource_id", nullable = false)
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
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

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="mon_group_id", referencedColumnName = "mon_group_id", insertable = false, updatable = false),
            @JoinColumn(name="server_resource_id", referencedColumnName = "server_resource_id", insertable = false, updatable = false)
    })
    public MgServer getMgServer() { return mgServer; }

    public void setMgServer(MgServer mgServer) { this.mgServer = mgServer; }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="metric_id", referencedColumnName = "id", insertable = false, updatable = false)
    })
    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerCriticalValue{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", metricId=").append(metricId);
        sb.append(", critical=").append(critical);
        sb.append(", warning=").append(warning);
        sb.append('}');
        return sb.toString();
    }
}
