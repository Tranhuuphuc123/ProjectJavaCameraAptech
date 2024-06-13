package com.camera.providers;

import com.camera.enums.Roles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebUrlProvider {
    public static final String ADMIN_PREFIX = "/admin";
    public static final String USER_PREFIX = "/user";

    private String addAdminPrefix(String path) {
        return ADMIN_PREFIX + path;
    }
    
    public class Site implements WebAuthUrl {
        
        //nhóm các site của Admin page
        public static final String LOGIN = "/login"; 
        public static final String USERS = "/users";
        public static final String SUPPLIERS = "/suppliers";
        public static final String CATEGORIES = "/categories";
        public static final String PRODUCTS = "/products";

        @Override
        public List<String> signInUrls() {
            ArrayList<String> signInUrls = new ArrayList<>();
            return signInUrls;
        }

        @Override
        public HashMap<String, List<String>> roleUrls() {
            HashMap<String, List<String>> roleUrls = new HashMap<>();
            
            /**
             * key: value
             * AMDIN: [url1, url2, url3]
             * EMPLOYEE: [url1, url2]
             * USER: [url1]
             * 
             * Filter read
             * request -> url2(ADMIN, EMPLOYEE): check token -> lay dc role -> check role -> di tiep|dung lai
             */
            
            // ADMIN urls
            List<String> adminUrls = new ArrayList<>();
            adminUrls.add(addAdminPrefix(USERS));
            adminUrls.add(addAdminPrefix(CATEGORIES));
            adminUrls.add(addAdminPrefix(PRODUCTS));
            
            roleUrls.put(Roles.ADMIN.name(), adminUrls);
            
            
            // EMPLOYEE urls
            List<String> employeeUrls = new ArrayList<>();
            employeeUrls.add(addAdminPrefix(PRODUCTS));
            
            roleUrls.put(Roles.EMPLOYEE.name(), employeeUrls);
            
            return roleUrls;
        }
        
    }
    
    
    
    
    public List<WebAuthUrl> getAllAuthUrl() {
        ArrayList<WebAuthUrl> result = new ArrayList<>();
        result.add(new Site());

        return result;
    }
}
