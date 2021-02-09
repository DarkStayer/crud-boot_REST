package com.andrew.common.crudboot.crudboot.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public String toString() {
        return name();
    }

    public static Set<Role> allRoles (){
        Set <Role> roles = new HashSet<>();
        Collections.addAll(roles, Role.values());
        return roles;
    }
}
