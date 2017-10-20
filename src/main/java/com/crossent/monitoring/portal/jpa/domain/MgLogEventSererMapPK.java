package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgLogEventSererMapPK implements Serializable {
    private Integer mgLogEventId;
    private Integer serverResourceId;
    private Integer monGroupId;

    @Column(name = "mg_log_event_id", nullable = false)
    @Id
    public Integer getMgLogEventId() {
        return mgLogEventId;
    }

    public void setMgLogEventId(Integer mgLogEventId) {
        this.mgLogEventId = mgLogEventId;
    }

    @Column(name = "server_resource_id", nullable = false)
    @Id
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgLogEventSererMapPK{");
        sb.append("mgLogEventId=").append(mgLogEventId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append('}');
        return sb.toString();
    }
}
