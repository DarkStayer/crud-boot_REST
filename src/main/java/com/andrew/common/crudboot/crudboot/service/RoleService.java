package com.andrew.common.crudboot.crudboot.service;

import com.andrew.common.crudboot.crudboot.model.Role;

import java.util.Set;

public interface RoleService {
    void save(Role role);
    void delete(Role role);
    Role getById(Long id);
    Role getRoleByName(String roleName);
    public Set<Role> getAllRoles ();
}
