package com.thiennam.messtar.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "MESSTAR_ROLE")
@Entity(name = "messtar_Role")
public class Role extends StandardEntity {
    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    private String roleName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    List<UserRole> userRoles;

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoleNameEnum getRoleName() {
        return RoleNameEnum.fromId(roleName);
    }

    public void setRoleName(RoleNameEnum roleName) {
        this.roleName = roleName == null ? null : roleName.getId();
    }
}
