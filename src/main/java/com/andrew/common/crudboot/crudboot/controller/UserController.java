package com.andrew.common.crudboot.crudboot.controller;

import com.andrew.common.crudboot.crudboot.model.User;
import com.andrew.common.crudboot.crudboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping (value = "user_page")
    public ModelAndView userInfo (Principal principal, ModelAndView model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addObject("user", user);
        model.setViewName("user_page");
        return model;
    }

    @RequestMapping (value = "")
    public String startPage0(){
       return "redirect:/login";
    }

    @RequestMapping (value = "/")
    public String startPage(){
        return "redirect:/login";
    }

}

