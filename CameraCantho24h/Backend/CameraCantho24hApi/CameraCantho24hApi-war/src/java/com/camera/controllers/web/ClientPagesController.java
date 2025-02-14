package com.camera.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ClientPagesController {
    @GetMapping({"", "/", "/home"})
    public String home() {
        return "client/home";
    }
      
    @GetMapping("/products")
    public String products() {
        return "client/products";
    }

    @GetMapping("/productdetail")
    public String productDetail() {
        return "client/productDetail";
    }
}


