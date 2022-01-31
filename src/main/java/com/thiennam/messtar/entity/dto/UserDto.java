package com.thiennam.messtar.entity.dto;

import com.google.gson.annotations.SerializedName;
import com.thiennam.messtar.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDto {
    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("password")
    private String password;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("avatarId")
    private String avatarId;
    @SerializedName("email")
    private String email;
    @SerializedName("description")
    private String description;
    @SerializedName("dob")
    private Long dob;
    @SerializedName("gender")
    private String gender;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("online")
    private Boolean online;
    @SerializedName("lastLogin")
    private Long lastLogin;

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = DateTimeUtil.toMillis(dob);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = DateTimeUtil.toMillis(lastLogin);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
