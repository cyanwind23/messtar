package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;
import com.thiennam.messtar.entity.User;

public class MessageDto {
    @SerializedName("messageId")
    private String messageId;
    @SerializedName("sender")
    private UserDto sender;
    @SerializedName("roomId")
    private String toRoomId;
    @SerializedName("toUser")
    private UserDto toUser;
    @SerializedName("type")
    private String type;
    @SerializedName("content")
    private String content;
    @SerializedName("createdTime")
    private Long createdMillis;
    @SerializedName("modified")
    private Long modifiedMillis;
    @SerializedName("status")
    private String status;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedMillis() {
        return createdMillis;
    }

    public void setCreatedMillis(Long createdMillis) {
        this.createdMillis = createdMillis;
    }

    public Long getModifiedMillis() {
        return modifiedMillis;
    }

    public void setModifiedMillis(Long modifiedMillis) {
        this.modifiedMillis = modifiedMillis;
    }

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public String getToRoomId() {
        return toRoomId;
    }

    public void setToRoomId(String toRoomId) {
        this.toRoomId = toRoomId;
    }

    public UserDto getToUser() {
        return toUser;
    }

    public void setToUser(UserDto toUser) {
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
