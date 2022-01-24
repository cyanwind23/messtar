package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;
import com.thiennam.messtar.entity.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomDto {
    @SerializedName("room_id")
    private String roomId;
    @SerializedName("room_name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("description")
    private String description;
    @SerializedName("created_time")
    private LocalDateTime createdTime;
    @SerializedName("last_active")
    private LocalDateTime lastActive;

    private List<Message> messages;

    private List<String> roomUsers = new ArrayList<>();

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<String> getRoomUsers() {
        return roomUsers;
    }

    public void setRoomUsers(List<String> roomUsers) {
        this.roomUsers = roomUsers;
    }
    public String getNameToDisplay() {
        return name.length() > 16 ? name.substring(0, 16) : name;
    }
}
