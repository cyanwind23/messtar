package com.thiennam.messtar.entity;

import org.springframework.lang.Nullable;

public enum UserMessageStatusEnum {
    UNSEEN("UNSEEN"),
    SEEN("SEEN"),
    DELETED("DELETED");

    private String id;

    UserMessageStatusEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static UserMessageStatusEnum fromId(String id) {
        for (UserMessageStatusEnum e : UserMessageStatusEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
