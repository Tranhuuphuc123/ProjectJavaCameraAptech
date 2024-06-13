package com.camera.providers;

import com.camera.enums.Roles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**providers cho api de xu ly json phan quyen
 => sau nay dung js(fetch..) de call api**/


public class UrlProvider {
    
    //thiết lập urlpatern chủ
    public static final String API_PREFIX = "/api";
    
    private String addApiPrefix(final String PATH) {
        return API_PREFIX + PATH;
    }
    
    
    
    //01  Nested class - thiết lập urlpatern con sau urlpatern chủ (ex: api/users/..(action)..)
    public class Users implements AuthUrl {
        
        public static final String PREFIX = "/users";
    
        private String addPrefix(final String PATH) {
            return PREFIX + PATH;
        }
        
        public static final String ALL = "/all";
        public static final String LOGIN = "/login";
        public static final String GET_MY_DATA = "/get_my_data";
        public static final String GENERATE_ADMIN_ACCOUNT = "/generate-admin-account";
        public static final String STORE = "/store";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";

        @Override
        public List<String> signInUrls() {
            ArrayList<String> signInUrls = new ArrayList<>();
            
            return signInUrls;
        }

        @Override
        public HashMap<String, List<String>> roleUrls() {
            HashMap<String, List<String>> roleUrls = new HashMap<>();
            
            // ADMIN urls
            List<String> adminUrls = new ArrayList<>();
            adminUrls.add(addApiPrefix(addPrefix(ALL)));
            adminUrls.add(addApiPrefix(addPrefix(STORE)));
            adminUrls.add(addApiPrefix(addPrefix(UPDATE)));
            adminUrls.add(addApiPrefix(addPrefix(DELETE)));
            adminUrls.add(addApiPrefix(addPrefix(GET_MY_DATA)));
            
            roleUrls.put(Roles.ADMIN.name(), adminUrls);
            
            // EMPLOYEE urls
            List<String> employeeUrls = new ArrayList<>();
            
            employeeUrls.add(addApiPrefix(addPrefix(GET_MY_DATA)));
            
            roleUrls.put(Roles.EMPLOYEE.name(), employeeUrls);
            
            // EMPLOYEE urls
            List<String> userUrls = new ArrayList<>();
            
            userUrls.add(addApiPrefix(addPrefix(GET_MY_DATA)));
            
            roleUrls.put(Roles.USER.name(), userUrls);
            
            return roleUrls;
        }
        
    }//end class Users
    
    
    
    
    
    //02 Nested class - tạo urlpatern con cho suppliers (ex: api/suppliers/....(action)..)
    public class Suppliers implements AuthUrl{
        //thiết lập urlpattern
        public static final String PREFIX = "/suppliers";

        // STORE(CREATE), ALL(READ), UPDATE, DELETE
        public static final String ALL = "/all";
        public static final String STORE = "/store";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        
        //method chức năng xủ lý 
        @Override
        public List<String> signInUrls() {
            ArrayList<String> signInUrls = new ArrayList<>();
            
            return signInUrls;
        }

        @Override
        public HashMap<String, List<String>> roleUrls() {
            HashMap<String, List<String>> roleUrls = new HashMap<>();
            
            return roleUrls;
        }
    }//end class suppliers
    
    
    
    
   // 03 Nested class - tạo urlpatern con cho category (ex: api/category/....(action)..)
    public class Category implements AuthUrl{
        //thiết lập urlpattern
        public static final String PREFIX = "/categories";

        // STORE(CREATE), ALL(READ), UPDATE, DELETE
        public static final String ALL = "/all";
        public static final String STORE = "/store";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        
        //method chức năng xủ lý 
        @Override
        public List<String> signInUrls() {
            ArrayList<String> signInUrls = new ArrayList<>();
            
            return signInUrls;
        }

        @Override
        public HashMap<String, List<String>> roleUrls() {
            HashMap<String, List<String>> roleUrls = new HashMap<>();
            
            return roleUrls;
        }
   }//end class category
    
    
    
    
    //04  Nested class - thiết lập urlpatern con sau urlpatern chủ (ex: api/users/..(action)..)
    public class Products implements AuthUrl {
        
        public static final String PREFIX = "/products";
    
        private String addPrefix(final String PATH) {
            return PREFIX + PATH;
        }
        
        public static final String ALL = "/all";
        public static final String STORE = "/store";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";

        @Override
        public List<String> signInUrls() {
            ArrayList<String> signInUrls = new ArrayList<>();
            
            return signInUrls;
        }

        @Override
        public HashMap<String, List<String>> roleUrls() {
            HashMap<String, List<String>> roleUrls = new HashMap<>();
            
            return roleUrls;
        }
        
    }//end class Users
    
    
   //...???....
    public List<AuthUrl> getAllAuthUrl() {
        ArrayList<AuthUrl> result = new ArrayList<>();
        result.add(new Users());
        result.add(new Suppliers());
        return result;
    }
    
    
    
}//end class UrlProvider


