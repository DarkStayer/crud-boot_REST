package com.andrew.common.crudboot.crudboot.service;

import com.andrew.common.crudboot.crudboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void removeUserById(long id);

    List <User> getAllUsers();

    User getUserById(long id);

    public UserDetails loadUserByUsername(String s);

}
