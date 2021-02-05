package com.andrew.common.crudboot.crudboot.DAO;

import com.andrew.common.crudboot.crudboot.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findRoleByName (String roleName);
    default Set<Role> getAllRoles (){
        Set <Role> roles = new HashSet<>();
        Role roleAdmin = new Role (2L,"ROLE_ADMIN");
        Role roleUser = new Role (1L,"ROLE_USER");
        roles.add(roleAdmin);
        roles.add(roleUser);
        return roles;
    }
}
