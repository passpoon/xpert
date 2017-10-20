package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mg_server_group_server", schema = "mondb", catalog = "")
@IdClass(MgServerGroupServerPK.class)
public class MgServerGroupServer implements Serializable {
    private Integer serverGroupId;
    private Integer monGroupId;
    private Integer serverResourceId;

    @Id
    @Column(name = "server_group_id", nullable = false)
    public Integer getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Integer serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    @Id
    @Column(name = "mon_group_id", nullable = false)
    public Integer getMonGroupId() {
        return monGroupId;
    }

    public void setMonGroupId(Integer monGroupId) {
        this.monGroupId = monGroupId;
    }

    @Id
    @Column(name = "server_resource_id", nullable = false)
    public Integer getServerResourceId() {
        return serverResourceId;
    }

    public void setServerResourceId(Integer serverResourceId) {
        this.serverResourceId = serverResourceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MgServerGroupServer{");
        sb.append("serverGroupId=").append(serverGroupId);
        sb.append(", monGroupId=").append(monGroupId);
        sb.append(", serverResourceId=").append(serverResourceId);
        sb.append('}');
        return sb.toString();
    }
}
