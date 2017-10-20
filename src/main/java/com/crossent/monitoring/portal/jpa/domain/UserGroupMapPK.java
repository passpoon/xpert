package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserGroupMapPK implements Serializable {
    private String userGroupId;
    private String userId;

    @Column(name = "user_group_id", nullable = false, length = 20)
    @Id
    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Column(name = "user_id", nullable = false, length = 20)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserGroupMapPK{");
        sb.append("userGroupId='").append(userGroupId).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
