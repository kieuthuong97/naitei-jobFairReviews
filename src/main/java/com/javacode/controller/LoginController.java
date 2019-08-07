package com.javacode.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(value = "/login")
    public String login(Model model, Authentication authentication) {
        if (authentication == null) {
        	return "views/login/login";
        }
        else {
        	return "redirect:/";
        }
    }
}

