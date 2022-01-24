package com.thiennam.messtar.entity;

import org.springframework.lang.Nullable;

public enum MessageTypeEnum {
    TEXT("TEXT"),
    IMG("IMG"),
    OTHER("OTHER");

    private String id;

    MessageTypeEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static MessageTypeEnum fromId(String id) {
        for (MessageTypeEnum e : MessageTypeEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return TEXT;
    }
}
