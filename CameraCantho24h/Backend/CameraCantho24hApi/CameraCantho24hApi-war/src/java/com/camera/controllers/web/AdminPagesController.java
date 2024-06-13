
package com.camera.controllers.web;

import com.camera.providers.WebUrlProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("" + WebUrlProvider.ADMIN_PREFIX)
public class AdminPagesController {
    
    @GetMapping({"", "/", "/home"})
    public String admin() {
        return "admin/home";
    }
    
    @GetMapping("" + WebUrlProvider.Site.LOGIN)
    public String login() {
        return "admin/login";
    }
    
    @GetMapping("" + WebUrlProvider.Site.SUPPLIERS) // -> /suppliers
    public String suppliers() {
        return "admin/suppliers";
    }
    
    @GetMapping("" + WebUrlProvider.Site.USERS)
    public String users() {
        return "admin/users";
    }
    
    @GetMapping("" + WebUrlProvider.Site.CATEGORIES)
    public String categories() {
        return "admin/categories";
    }
    
    @GetMapping("" + WebUrlProvider.Site.PRODUCTS)
    public String products() {
        return "admin/products";
    }
    
}