package edu.mum.cs.onlinehabeshaclothing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/")
    public String showProduct(){
        return "product";
    }
}
