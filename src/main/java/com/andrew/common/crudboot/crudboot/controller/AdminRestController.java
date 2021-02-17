package com.andrew.common.crudboot.crudboot.controller;

import com.andrew.common.crudboot.crudboot.exception_handler.NoUserException;
import com.andrew.common.crudboot.crudboot.model.Role;
import com.andrew.common.crudboot.crudboot.model.User;
import com.andrew.common.crudboot.crudboot.service.ConverterToHash;
import com.andrew.common.crudboot.crudboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ConverterToHash converterToHash;

    @GetMapping(value = "/admin_page/users")
    public List <User> showAllUsers (){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/admin_page/roles")
    public Set<Role> getAllUsers (){
        return Role.allRoles();
    }

    @GetMapping(value = "/admin_page/users/{id}")
    public User getUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        if (user.getUsername()==null){
            throw new NoUserException("These aren't User with id="+ id +" you're looking for");
        }
        return user;
    }

    @PostMapping(value = "/admin_page/users")
    public ResponseEntity<User> addNewUser (@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping (value = "/admin_page/users")
    public ResponseEntity<User> editUser (@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin_page/users/{id}")
    public void removeUser (@PathVariable Long id){
        User user = userService.getUserById(id);
        if (user.getUsername()==null){
            throw new NoUserException("These aren't User with id="+ id +" you're looking for");
        }
        userService.removeUserById(id);
    }
}
