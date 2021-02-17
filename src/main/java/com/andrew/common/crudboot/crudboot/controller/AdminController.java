package com.andrew.common.crudboot.crudboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping(value = "/admin_page")
    public String admin_page (){
        return "admin_page";
    }
}
