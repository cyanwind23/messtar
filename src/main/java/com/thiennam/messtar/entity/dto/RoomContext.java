package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomContext {
    @SerializedName("room")
    private RoomDto room;
    @SerializedName("loggedUser")
    private String loggedUser;

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
