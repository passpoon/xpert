package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAlarmServerMapPK implements Serializable {
    private Integer mgAlarmId;
    private Integer serverResourceId;
    private Integer monGroupId;

    @Column(name = "mg_alarm_id", nullable = false)
    @Id
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Column(name = "server_resource_id", nullable = false)
    @Id
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    @Column(name = "mon_group_id", nullable = false)
    @Id
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmServerMapPK{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append('}');
        return sb.toString();
    }
}
