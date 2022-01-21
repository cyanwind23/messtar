package com.thiennam.messtar.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "MESSTAR_FRIENDSHIP")
@Entity(name = "messtar_Friendship")
public class Friendship extends StandardEntity {
    @ManyToOne
    @JoinColumn(name = "USER1", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "USER2", nullable = false)
    private User user2;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "CREATED_TIME", nullable = false)
    private LocalDateTime createdTime;

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }
}
