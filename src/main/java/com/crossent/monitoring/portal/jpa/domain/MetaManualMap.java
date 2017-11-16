package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meta_manual_map", schema = "mondb", catalog = "")
@IdClass(MetaManualMapPK.class)
public class MetaManualMap implements Serializable {
    private Integer metaId;
    private Integer manualId;

    @Id
    @Column(name = "meta_id", nullable = false)
    public Integer getMetaId() { return metaId; }

    public void setMetaId(Integer metaId) { this.metaId = metaId; }

    @Id
    @Column(name = "manual_id", nullable = false)
    public Integer getManualId() { return manualId; }

    public void setManualId(Integer manualId) { this.manualId = manualId; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetaManualMap{");
        sb.append("metaId=").append(metaId);
        sb.append(", manualId=").append(manualId);
        sb.append('}');
        return sb.toString();
    }
}
