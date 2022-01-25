package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;
import com.thiennam.messtar.entity.Message;

import java.util.List;

public class RoomContext {
    @SerializedName("room")
    private RoomDto room;
    @SerializedName("loggedUser")
    private String loggedUser;
    @SerializedName("messages")
    private List<MessageDto> messages;

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }
}
