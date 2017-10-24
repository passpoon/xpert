package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgServerPK implements Serializable{
    private Integer monGroupId;
    private Integer serverResourceId;

    @Id
    @Column(name = "mon_group_id", nullable = false, insertable = false, updatable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Id
    @Column(name = "server_resource_id", nullable = false, insertable = false, updatable = false)
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerPK{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgServerPK that = (MgServerPK) o;

        if (monGroupId != null ? !monGroupId.equals(that.monGroupId) : that.monGroupId != null) return false;
        return serverResourceId != null ? serverResourceId.equals(that.serverResourceId) : that.serverResourceId == null;
    }

    @Override
    public int hashCode() {
        int result = monGroupId != null ? monGroupId.hashCode() : 0;
        result = 31 * result + (serverResourceId != null ? serverResourceId.hashCode() : 0);
        return result;
    }
}
