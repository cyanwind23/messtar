package com.thiennam.messtar.entity;

import org.springframework.lang.Nullable;

public enum RoleNameEnum {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    USER("USER");

    private String id;

    RoleNameEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static RoleNameEnum fromId(String id) {
        for (RoleNameEnum e : RoleNameEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
