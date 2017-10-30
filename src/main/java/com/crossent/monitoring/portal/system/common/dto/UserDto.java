package com.crossent.monitoring.portal.system.common.dto;

import com.crossent.monitoring.portal.jpa.domain.MonGroup;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class UserDto implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String chatId;
    private String description;
    private String uuid;
    private List<MonGroupDto> monGroups;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getChatId() { return chatId; }

    public void setChatId(String chatId) { this.chatId = chatId; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    public List<MonGroupDto> getMonGroups() { return monGroups; }

    public void setMonGroups(List<MonGroupDto> monGroups) {
        this.monGroups = monGroups;
    }


}
