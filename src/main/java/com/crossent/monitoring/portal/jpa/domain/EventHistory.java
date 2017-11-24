package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "event_history", schema = "mondb")
public class EventHistory implements Serializable {
    private Long id;
    private String resourceType;
    private Integer resourceId;
    private String hostname;
    private String ip;
    private String program;
    private String contents;
    private String useYn;
    private String registDttm;
    private String updateDttm;
    private MonGroup monGroup;
    private StateCode stateCode;
    private String resourceUuid;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "resource_type", nullable = false, length = 20)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "resource_id", nullable = false)
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "hostname", nullable = true, length = 100)
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 50)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "program", nullable = true, length = 100)
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Basic
    @Column(name = "contents", nullable = true, length = 2000)
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Basic
    @Column(name = "use_yn", nullable = true, length = 1)
    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    @Basic
    @Column(name = "regist_dttm", nullable = true, length = 14)
    public String getRegistDttm() {
        return registDttm;
    }

    public void setRegistDttm(String registDttm) {
        this.registDttm = registDttm;
    }

    @Basic
    @Column(name = "update_dttm", nullable = true, length = 14)
    public String getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(String updateDttm) {
        this.updateDttm = updateDttm;
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
    @JoinColumn(name = "state_code", referencedColumnName = "code", nullable = false)
    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    @Basic
    @Column(name = "resource_uuid", nullable = true, length = 100)
    public String getResourceUuid() { return resourceUuid; }

    public void setResourceUuid(String resourceUuid) { this.resourceUuid = resourceUuid; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventHistory{");
        sb.append("id=").append(id);
        sb.append(", resourceType='").append(resourceType).append('\'');
        sb.append(", resourceId=").append(resourceId);
        sb.append(", hostname='").append(hostname).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", program='").append(program).append('\'');
        sb.append(", contents='").append(contents).append('\'');
        sb.append(", useYn='").append(useYn).append('\'');
        sb.append(", registDttm='").append(registDttm).append('\'');
        sb.append(", updateDttm='").append(updateDttm).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
