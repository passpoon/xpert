package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgServerGroupCriticalValuePK implements Serializable {
    private Integer serverGroupId;
    private Integer metricId;

    @Column(name = "server_group_id", nullable = false)
    @Id
    public Integer getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Integer serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    @Column(name = "metric_id", nullable = false)
    @Id
    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerGroupCriticalValuePK{");
        sb.append("serverGroupId=").append(serverGroupId);
        sb.append(", metricId=").append(metricId);
        sb.append('}');
        return sb.toString();
    }
}
