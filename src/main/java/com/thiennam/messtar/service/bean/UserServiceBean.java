package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.*;
import com.thiennam.messtar.entity.dto.UserDto;
import com.thiennam.messtar.repository.UserRepository;
import com.thiennam.messtar.repository.UserRoleRepository;
import com.thiennam.messtar.service.RoleService;
import com.thiennam.messtar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = UserService.NAME)
public class UserServiceBean implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId().toString());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        // set avatar
        MesStarResource avt = user.getAvatar();
        userDto.setAvatarId(avt == null ? "/img/profile.png" : "/storage?rid=" + avt.getId().toString());
        userDto.setEmail(user.getEmail());
        userDto.setDescription(user.getDescription());
        userDto.setDob(user.getDob());
        userDto.setGender(userDto.getGender());
        userDto.setLastLogin(user.getLastLogin());
        userDto.setPhoneNumber(userDto.getPhoneNumber());
        userDto.setOnline(user.isOnline());
        return userDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setStatus(UserStatusEnum.ACTIVE);

        return user;
    }

    @Override
    public void addNewUser(User user) {
        Role role = roleService.findByRoleName(RoleNameEnum.USER.getId());

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        user.setUserRoles(Arrays.asList(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}
