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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgAlarmUserPK that = (MgAlarmUserPK) o;

        if (mgAlarmId != null ? !mgAlarmId.equals(that.mgAlarmId) : that.mgAlarmId != null) return false;
        return mgUserId != null ? mgUserId.equals(that.mgUserId) : that.mgUserId == null;
    }

    @Override
    public int hashCode() {
        int result = mgAlarmId != null ? mgAlarmId.hashCode() : 0;
        result = 31 * result + (mgUserId != null ? mgUserId.hashCode() : 0);
        return result;
    }
}
