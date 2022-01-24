package com.thiennam.messtar.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "MESSTAR_USER")
@Entity(name = "messtar_User")
public class User extends StandardEntity {
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "ENCRYPTED_PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LAST_LOGIN")
    private LocalDate lastLogin;

    @OneToMany(mappedBy = "user1", fetch = FetchType.LAZY)
    private List<Friendship> friendships;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RoomUser> roomUsers = new ArrayList<>();

    public List<RoomUser> getRoomUsers() {
        return roomUsers;
    }

    public void setRoomUsers(List<RoomUser> roomUsers) {
        this.roomUsers = roomUsers;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(List<Friendship> friendships) {
        this.friendships = friendships;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserStatusEnum getStatus() {
        return UserStatusEnum.fromId(status);
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status == null ? null : status.getId();
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
}
