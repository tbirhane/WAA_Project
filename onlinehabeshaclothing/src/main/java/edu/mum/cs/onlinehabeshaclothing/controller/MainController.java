package edu.mum.cs.onlinehabeshaclothing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/login")
    public String logedIn(Model model) {

        return "redirect:/";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
