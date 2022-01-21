package com.thiennam.messtar.entity;

import org.springframework.lang.Nullable;

public enum UserStatusEnum {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String id;
    UserStatusEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static UserStatusEnum fromId(String id) {
        for (UserStatusEnum e : UserStatusEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
