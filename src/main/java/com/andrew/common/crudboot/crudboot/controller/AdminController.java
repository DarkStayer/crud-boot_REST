package com.andrew.common.crudboot.crudboot.controller;

import com.andrew.common.crudboot.crudboot.model.Role;
import com.andrew.common.crudboot.crudboot.model.User;
import com.andrew.common.crudboot.crudboot.service.ConverterToHash;
import com.andrew.common.crudboot.crudboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ConverterToHash converterToHash;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/admin_page")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<User> allUser = userService.getAllUsers();
        modelAndView.setViewName("admin_page");
        modelAndView.addObject("listUser", allUser);
        modelAndView.addObject("user", new User ());
        modelAndView.addObject("roleSet", Role.allRoles());
        return modelAndView;
    }

    @PostMapping(value = "admin/save")
    public String saveUser(@RequestParam (value = "id", required = false) Long id,
                           @RequestParam ("firstName") String name,
                           @RequestParam ("lastName") String lastName,
                           @RequestParam ("age") Byte age,
                           @RequestParam ("username") String username,
                           @RequestParam ("password") String password,
                           @RequestParam (value = "roles", required = false) String [] roles) {
        User user = new User (name, lastName, age, username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        if (!(id==null)) {
            user.setId(id);
        }
        if(!(roles ==null)) {
            Set<Role> roleSet = converterToHash.convert(roles);
            user.setRoles(roleSet);
        }
        userService.addUser(user);
        return "redirect:/admin_page";
    }


    @RequestMapping("admin/delete")
    public String deleteUser(@RequestParam ("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin_page";
    }
}
