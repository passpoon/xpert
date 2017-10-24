package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgServerGroupServerPK implements Serializable {
    private Integer serverGroupId;
    private Integer monGroupId;
    private Integer serverResourceId;

    @Column(name = "server_group_id", nullable = false)
    @Id
    public Integer getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Integer serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    @Column(name = "mon_group_id", nullable = false)
    @Id
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Column(name = "server_resource_id", nullable = false)
    @Id
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerGroupServerPK{");
        sb.append("serverGroupId=").append(serverGroupId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgServerGroupServerPK that = (MgServerGroupServerPK) o;

        if (serverGroupId != null ? !serverGroupId.equals(that.serverGroupId) : that.serverGroupId != null)
            return false;
        if (monGroupId != null ? !monGroupId.equals(that.monGroupId) : that.monGroupId != null) return false;
        return serverResourceId != null ? serverResourceId.equals(that.serverResourceId) : that.serverResourceId == null;
    }

    @Override
    public int hashCode() {
        int result = serverGroupId != null ? serverGroupId.hashCode() : 0;
        result = 31 * result + (monGroupId != null ? monGroupId.hashCode() : 0);
        result = 31 * result + (serverResourceId != null ? serverResourceId.hashCode() : 0);
        return result;
    }
}
