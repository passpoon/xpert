package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_alarm_server_map", schema = "mondb")
@IdClass(MgAlarmServerMapPK.class)
public class MgAlarmServerMap implements Serializable {
    private Integer mgAlarmId;
    private Integer serverResourceId;
    private Integer monGroupId;

    @Id
    @Column(name = "mg_alarm_id", nullable = false)
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
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
        final StringBuilder sb = new StringBuilder("MgAlarmServerMap{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append('}');
        return sb.toString();
    }
}
