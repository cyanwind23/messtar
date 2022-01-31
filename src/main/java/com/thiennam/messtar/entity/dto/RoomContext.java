package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;
import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.User;

import java.util.List;

public class RoomContext {
    @SerializedName("room")
    private RoomDto room;
    @SerializedName("loggedUser")
    private UserDto loggedUser;
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

    public UserDto getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserDto loggedUser) {
        this.loggedUser = loggedUser;
    }
}
