package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;

public class AddFriendRequestDto {
    @SerializedName("loggedUser")
    private String loggedUser;
    @SerializedName("userToAdd")
    private String userToAdd;

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getUserToAdd() {
        return userToAdd;
    }

    public void setUserToAdd(String userToAdd) {
        this.userToAdd = userToAdd;
    }
}
