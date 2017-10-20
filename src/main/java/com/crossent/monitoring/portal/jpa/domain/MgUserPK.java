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
}
