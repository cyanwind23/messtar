package com.thiennam.messtar;

import com.thiennam.messtar.entity.Role;
import com.thiennam.messtar.entity.RoleNameEnum;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.UserRole;
import com.thiennam.messtar.service.RoleService;
import com.thiennam.messtar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class UserRoleTest {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Test
    public void addUserRole() {
        User user = userService.findByUsername("thiennam23");
        if (user != null) {
            Role adminRole = roleService.findByRoleName(RoleNameEnum.ADMIN.getId());
            Role userRole = roleService.findByRoleName(RoleNameEnum.USER.getId());

            UserRole userRole1 = new UserRole();
            userRole1.setUser(user);
            userRole1.setRole(adminRole);
            userRole1.setStatus("ACTIVE");

            UserRole userRole2 = new UserRole();
            userRole2.setUser(user);
            userRole2.setRole(userRole);
            userRole2.setStatus("ACTIVE");

            user.setUserRoles(Arrays.asList(userRole1, userRole2));
            userService.save(user);
        }
    }
}
