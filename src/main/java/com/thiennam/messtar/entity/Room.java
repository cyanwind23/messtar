package com.thiennam.messtar.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "MESSTAR_ROOM")
@Entity(name = "messtar_Room")
public class Room extends StandardEntity {
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_TIME", nullable = false)
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Message> messages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
