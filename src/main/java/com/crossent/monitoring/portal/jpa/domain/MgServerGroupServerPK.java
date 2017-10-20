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
}
