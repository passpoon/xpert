package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MgUserPK implements Serializable{
    private Integer monGroupId;
    private String userId;

    @Id
    @Column(name = "mon_group_id", nullable = false, updatable = false, insertable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }


    @Id
    @Column(name = "user_id", nullable = false, updatable = false, insertable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgUserPK{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", userId='").append(userId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgUserPK mgUserPK = (MgUserPK) o;

        if (monGroupId != null ? !monGroupId.equals(mgUserPK.monGroupId) : mgUserPK.monGroupId != null) return false;
        return userId != null ? userId.equals(mgUserPK.userId) : mgUserPK.userId == null;
    }

    @Override
    public int hashCode() {
        int result = monGroupId != null ? monGroupId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
