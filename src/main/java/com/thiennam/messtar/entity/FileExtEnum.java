package com.thiennam.messtar.entity;

public enum FileExtEnum {
    TXT("TXT"),
    MP3("MP3"),
    MP4("MP4"),
    JPG("JPG"),
    PNG("PNG"),
    JPEG("JPEG"),
    OTHER("OTHER");

    private String id;

    FileExtEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FileExtEnum fromId(String id) {
        id = id.toUpperCase();
        for (FileExtEnum e : FileExtEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return OTHER;
    }
}
