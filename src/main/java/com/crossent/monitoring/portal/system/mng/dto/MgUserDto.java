package com.crossent.monitoring.portal.system.mng.dto;

import com.crossent.monitoring.portal.jpa.domain.MgUserPK;
import com.crossent.monitoring.portal.jpa.domain.MonGroup;
import com.crossent.monitoring.portal.jpa.domain.TypeCode;
import com.crossent.monitoring.portal.jpa.domain.User;

import javax.persistence.*;
import java.io.Serializable;

public class MgUserDto implements Serializable {
    private Integer monGroupId;
    private String userId;
    private String userName;
    private String monGroupName;
    private User user;
    private String typeCode;
    private String typeName;


    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) { this.monGroupId = monGroupId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }





    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMonGroupName() {
        return monGroupName;
    }

    public void setMonGroupName(String monGroupName) {
        this.monGroupName = monGroupName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "MgUserDto{" +
                "monGroupId=" + monGroupId +
                ", userId='" + userId + '\'' +
                ", monGroupName='" + monGroupName + '\'' +
                ", user=" + user +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
