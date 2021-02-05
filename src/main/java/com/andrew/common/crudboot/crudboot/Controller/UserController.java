package com.andrew.common.crudboot.crudboot.Controller;

import com.andrew.common.crudboot.crudboot.Model.User;
import com.andrew.common.crudboot.crudboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ModelAndView showUser(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/")
    public String getLoginPage() {
        return "index";
    }

}
