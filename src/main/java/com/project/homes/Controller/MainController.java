package com.project.homes.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess() {
        try{
            return "login:/login?success";

        } catch (Exception e)
        {
            e.printStackTrace();
            return "login:/login?error";
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}