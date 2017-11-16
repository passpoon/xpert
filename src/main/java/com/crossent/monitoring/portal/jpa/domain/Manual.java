package com.crossent.monitoring.portal.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "manual", schema = "mondb")
public class Manual implements Serializable {
    private Integer id;
    private String name;
    private String link;
    private String description;

    @JsonIgnore
    private Collection<Meta> metas;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "link", nullable = true, length = 200)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "meta_manual_map",
            joinColumns = @JoinColumn(name = "manual_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "meta_id", referencedColumnName="id"))
    public Collection<Meta> getMetas() { return metas; }

    public void setMetas(Collection<Meta> metas) { this.metas = metas; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Manual{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
