package com.thiennam.messtar;

import com.thiennam.messtar.entity.Role;
import com.thiennam.messtar.entity.RoleNameEnum;
import com.thiennam.messtar.repository.RoleRepository;
import com.thiennam.messtar.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void insertRole() {
        Role role = new Role();
        role.setRoleName(RoleNameEnum.USER);
        role.setDescription("User role");
        role.setStatus("ACTIVE");

        roleRepository.save(role);
    }
}
