package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;

public class MessageDto {
    @SerializedName("sender")
    private String senderName;
    @SerializedName("room_id")
    private String toRoomId;
    @SerializedName("to_username")
    private String toUsername;
    @SerializedName("type")
    private String type;
    @SerializedName("content")
    private String content;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getToRoomId() {
        return toRoomId;
    }

    public void setToRoomId(String toRoomId) {
        this.toRoomId = toRoomId;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
