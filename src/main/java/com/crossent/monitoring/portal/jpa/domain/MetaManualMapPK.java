package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MetaManualMapPK implements Serializable {
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
        final StringBuilder sb = new StringBuilder("MetaManualMapPK{");
        sb.append("metaId=").append(metaId);
        sb.append(", manualId=").append(manualId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaManualMapPK that = (MetaManualMapPK) o;

        if (metaId != null ? !metaId.equals(that.metaId) : that.metaId != null) return false;
        return manualId != null ? manualId.equals(that.manualId) : that.manualId == null;
    }

    @Override
    public int hashCode() {
        int result = metaId != null ? metaId.hashCode() : 0;
        result = 31 * result + (manualId != null ? manualId.hashCode() : 0);
        return result;
    }
}
