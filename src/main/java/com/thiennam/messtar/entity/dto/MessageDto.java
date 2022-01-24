package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;

public class MessageDto {
    @SerializedName("sender")
    private String sender;
    @SerializedName("roomId")
    private String toRoomId;
    @SerializedName("toUser")
    private String toUser;
    @SerializedName("type")
    private String type;
    @SerializedName("content")
    private String content;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getToRoomId() {
        return toRoomId;
    }

    public void setToRoomId(String toRoomId) {
        this.toRoomId = toRoomId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
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
