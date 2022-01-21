package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.Role;

public interface RoleService {
    String NAME = "messtar_RoleService";

    Role findByRoleName(String roleName);
}
