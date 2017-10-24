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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgServerGroupCriticalValuePK that = (MgServerGroupCriticalValuePK) o;

        if (serverGroupId != null ? !serverGroupId.equals(that.serverGroupId) : that.serverGroupId != null)
            return false;
        return metricId != null ? metricId.equals(that.metricId) : that.metricId == null;
    }

    @Override
    public int hashCode() {
        int result = serverGroupId != null ? serverGroupId.hashCode() : 0;
        result = 31 * result + (metricId != null ? metricId.hashCode() : 0);
        return result;
    }
}
