package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.Role;
import com.thiennam.messtar.repository.RoleRepository;
import com.thiennam.messtar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = RoleService.NAME)
public class RoleServiceBean implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
