package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.UserDto;

import java.util.List;

public interface UserService {
    String NAME = "messtar_UserService";

    User findByUsername(String username);

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);

    void addNewUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void save(User user);
}
