package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgAlarmUserPK implements Serializable {
    private Integer mgAlarmId;
    private String mgUserId;

    @Column(name = "mg_alarm_id", nullable = false, insertable = false, updatable = false)
    @Id
    public Integer getMgAlarmId() {
        return mgAlarmId;
    }

    public void setMgAlarmId(Integer mgAlarmId) {
        this.mgAlarmId = mgAlarmId;
    }

    @Column(name = "mg_user_id", nullable = false, length = 20, insertable = false, updatable = false)
    @Id
    public String getMgUserId() {
        return mgUserId;
    }

    public void setMgUserId(String mgUserId) {
        this.mgUserId = mgUserId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgAlarmUserPK{");
        sb.append("mgAlarmId=").append(mgAlarmId);
        sb.append(", mgUserId='").append(mgUserId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
