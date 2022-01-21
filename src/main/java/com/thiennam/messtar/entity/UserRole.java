package com.thiennam.messtar.entity;

import javax.persistence.*;

@Table(name = "MESSTAR_USER_ROLE")
@Entity(name = "messtar_UserRole")
public class UserRole extends StandardEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @Column(name = "STATUS")
    private String status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
