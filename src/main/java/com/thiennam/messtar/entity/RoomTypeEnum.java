package com.thiennam.messtar.entity;

import org.springframework.lang.Nullable;

public enum RoomTypeEnum {
    SINGLE("SINGLE"),
    MULTIPLE("MULTIPLE");

    private String id;

    RoomTypeEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static RoomTypeEnum fromId(String id) {
        for (RoomTypeEnum e : RoomTypeEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
