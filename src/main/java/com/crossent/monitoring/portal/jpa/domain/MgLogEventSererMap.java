package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_log_event_server_map", schema = "mondb")
@IdClass(MgLogEventSererMapPK.class)
public class MgLogEventSererMap implements Serializable {
    private Integer mgLogEventId;
    private Integer serverResourceId;
    private Integer monGroupId;

    @Id
    @Column(name = "mg_log_event_id", nullable = false)
    public Integer getMgLogEventId() {
        return mgLogEventId;
    }

    public void setMgLogEventId(Integer mgLogEventId) {
        this.mgLogEventId = mgLogEventId;
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
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgLogEventSererMap{");
        sb.append("mgLogEventId=").append(mgLogEventId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append('}');
        return sb.toString();
    }
}
