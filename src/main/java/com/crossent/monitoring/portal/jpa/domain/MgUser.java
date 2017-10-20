package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_user", schema = "mondb")
@IdClass(MgUserPK.class)
public class MgUser implements Serializable {
    private Integer monGroupId;
    private String userId;
    private MonGroup monGroup;
    private User user;
    private TypeCode typeCode;


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


    @ManyToOne
    @JoinColumn(name = "mon_group_id", referencedColumnName = "id", nullable = false)
    public MonGroup getMonGroup() {
        return monGroup;
    }

    public void setMonGroup(MonGroup monGroup) {
        this.monGroup = monGroup;
    }


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToOne
    @JoinColumn(name = "user_type_code", referencedColumnName = "code", nullable = false)
    public TypeCode getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(TypeCode typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgUser{");
        sb.append("monGroupId=").append(monGroupId);
        sb.append(", userId='").append(userId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
