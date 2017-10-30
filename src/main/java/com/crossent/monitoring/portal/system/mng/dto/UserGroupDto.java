package com.crossent.monitoring.portal.system.mng.dto;

import com.crossent.monitoring.portal.jpa.domain.User;

import java.io.Serializable;
import java.util.Collection;

public class UserGroupDto implements Serializable {
    private String id;
    private String name;
    private String description;
    private Collection<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

}
