package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAlarmServerGroupMapPK implements Serializable {
    private Integer mgAlarmId;
    private Integer mgServerGroupId;

    @Column(name = "mg_alarm_id", nullable = false)
    @Id
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Column(name = "mg_server_group_id", nullable = false)
    @Id
    public Integer getMgServerGroupId() {
        return mgServerGroupId;
    }

    public void setMgServerGroupId(Integer mgServerGroupId) {
        this.mgServerGroupId = mgServerGroupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmServerGroupMapPK{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", mgServerGroupId=").append(mgServerGroupId);
        sb.append('}');
        return sb.toString();
    }
}
