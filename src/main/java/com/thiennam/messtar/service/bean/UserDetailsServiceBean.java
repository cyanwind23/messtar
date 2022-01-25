package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.MyUserDetails;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.UserRole;
import com.thiennam.messtar.repository.UserRepository;
import com.thiennam.messtar.repository.UserRoleRepository;
import com.thiennam.messtar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceBean implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        // if user not null then fetch roles
        List<UserRole> userRoles = userRoleRepository.findByUser(user);
        user.setUserRoles(userRoles);
        return new MyUserDetails(user);
    }
}
