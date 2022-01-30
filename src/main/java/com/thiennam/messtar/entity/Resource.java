package com.thiennam.messtar.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "MESSTAR_RESOURCE")
@Entity(name = "messtar_Resource")
public class Resource extends StandardEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "path")
    private String path;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileExtEnum getType() {
        return FileExtEnum.fromId(type);
    }

    public void setType(FileExtEnum type) {
        this.type = type == null ? FileExtEnum.OTHER.getId() : type.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}