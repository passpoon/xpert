package com.crossent.monitoring.portal.mongroup.monitoring.dto;

public class EventResDto {

    Long id;
    Integer monGroupId;
    String resourceType;
    String hostName;
    String ip;
    String resourceUuid;
    String resourceName;
    String program;
    String contents;
    String regiDttm;
    String updateDttm;
    String state;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getResourceUuid() {
        return resourceUuid;
    }

    public void setResourceUuid(String resourceUuid) {
        this.resourceUuid = resourceUuid;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRegiDttm() {
        return regiDttm;
    }

    public void setRegiDttm(String regiDttm) {
        this.regiDttm = regiDttm;
    }

    public String getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(String updateDttm) {
        this.updateDttm = updateDttm;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventResDto{");
        sb.append("id=").append(id);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append(", resourceType='").append(resourceType).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", resourceUuid='").append(resourceUuid).append('\'');
        sb.append(", resourceName='").append(resourceName).append('\'');
        sb.append(", program='").append(program).append('\'');
        sb.append(", contents='").append(contents).append('\'');
        sb.append(", regiDttm='").append(regiDttm).append('\'');
        sb.append(", updateDttm='").append(updateDttm).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
