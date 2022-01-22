package com.thiennam.messtar.entity;

import org.springframework.lang.Nullable;

public enum FriendshipTypeEnum {
    FRIEND("FRIEND"),
    FATHER("FATHER"),
    MOTHER("MOTHER");

    private String id;

    FriendshipTypeEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static FriendshipTypeEnum fromId(String id) {
        for (FriendshipTypeEnum e : FriendshipTypeEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
