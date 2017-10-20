package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_group_map", schema = "mondb", catalog = "")
@IdClass(UserGroupMapPK.class)
public class UserGroupMap implements Serializable {
    private String userGroupId;
    private String userId;

    @Id
    @Column(name = "user_group_id", nullable = false, length = 20)
    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserGroupMap{");
        sb.append("userGroupId='").append(userGroupId).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
