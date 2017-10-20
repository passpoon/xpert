package com.crossent.monitoring.portal.jpa.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Test implements Serializable {
    private Integer id;
    private String col1;
    private String col2;
    private String col3;
    private String col4;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "col1", nullable = false, length = 100)
    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    @Basic
    @Column(name = "col2", nullable = true, length = 200)
    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    @Basic
    @Column(name = "col3", nullable = true, length = 200)
    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    @Basic
    @Column(name = "col4", nullable = true, length = 200)
    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Test{");
        sb.append("id=").append(id);
        sb.append(", col1='").append(col1).append('\'');
        sb.append(", col2='").append(col2).append('\'');
        sb.append(", col3='").append(col3).append('\'');
        sb.append(", col4='").append(col4).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
