package com.andrew.common.crudboot.crudboot.controller;

import com.andrew.common.crudboot.crudboot.model.Role;
import com.andrew.common.crudboot.crudboot.model.User;
import com.andrew.common.crudboot.crudboot.service.ConverterToHash;
import com.andrew.common.crudboot.crudboot.service.RoleService;
import com.andrew.common.crudboot.crudboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    ConverterToHash converterToHash;

    @RequestMapping("/admin")
    public ModelAndView userList() {
        List<User> list = userService.getAllUsers();
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("listUsers", list);
        return mav;
    }

    @RequestMapping("/admin/newUser")
    public String newUserForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "userForm";
    }

    @PostMapping(value = "admin/save")
    public String saveUser(@RequestParam (value = "id", required = false) Long id,
                           @RequestParam ("name") String name,
                           @RequestParam ("lastName") String lastName,
                           @RequestParam ("age") byte age,
                           @RequestParam ("password") String password,
                           @RequestParam (value = "roles", required = false) String [] roles) {
        User user = new User (name, lastName, age);
        user.setPassword(password);
        if (!(id==null)) {
            user.setId(id);
        }
        if(!(roles ==null)) {
            Set<Role> roleSet = converterToHash.convert(roles);
            user.setRoles(roleSet);
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit")
    public ModelAndView editUser(@RequestParam long id, ModelAndView mav) {
        mav = new ModelAndView("edit_user");
        User user = userService.getUserById(id);
        Set<Role> roles = roleService.getAllRoles();
        mav.addObject("roles", roles);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("admin/delete")
    public String deleteUser(@RequestParam long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
