package com.camera.controllers.api;

import com.camera.dto.users.AllUsersResDTO;
import com.camera.entities.Suppliers;
import com.camera.entities.Users;
import com.camera.enums.RequestAttributeKeys;
import com.camera.enums.Roles;
import com.camera.enums.TokenNames;
import com.camera.payload.requests.users.CreateUserReq;
import com.camera.payload.requests.users.LoginRequest;
import com.camera.payload.requests.users.UpdateUsersReq;
import com.camera.payload.responses.DataResponse;
import com.camera.payload.responses.LoginResponse;
import com.camera.payload.responses.StandardResponse;
import com.camera.providers.UrlProvider;
import com.camera.services.UserTokenService;
import com.camera.session_beans.UsersFacadeLocal;
import com.camera.supports.FileSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//viết api (khởi tạo api bên java spring(giống như khởi tạo một api bên nodejs))


//00 mục này insert into value lên table Users
@RestController
@RequestMapping("" + UrlProvider.API_PREFIX + UrlProvider.Users.PREFIX)
public class ManageUsersApiController {

    UsersFacadeLocal usersFacade = lookupUsersFacadeLocal();
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserTokenService userTokenService;
    
    // Generate admin account for first time
    // @GetMapping("" + UrlProvider.Users.GENERATE_ADMIN_ACCOUNT)
    public ResponseEntity<StandardResponse> generateAdminAccount() {
        Users newUser = new Users();
        newUser.setFullName("Admin User");
        newUser.setEmail("admin@cameracantho24h.com");
        newUser.setPhone("0123456789");
        newUser.setPassword(passwordEncoder.encode("12345678"));
        newUser.setAddress("Address");
        newUser.setAvatar("avatar.jpg");
        newUser.setGender("Male");
        newUser.setRole(Roles.ADMIN.name());
        newUser.setForgotPasswordCode(null);
        newUser.setEmailVerifyAt(new Date());
        newUser.setEmailVerifyCode(null);
        
        usersFacade.create(newUser);
        
        StandardResponse res = StandardResponse.builder()
                .success(true)
                .code(200)
                .message("Successfully generate new admin account")
                .build();
        
        return ResponseEntity.ok(res);
    }
    
    
    
    
    // API trả về toàn bộ user trong db
    //01 đỗ dữ liệu từ sql lên table user manager trên giao diện  admin
    @GetMapping("" + UrlProvider.Users.ALL)
    public ResponseEntity<DataResponse<List<AllUsersResDTO>>> allUsers(HttpServletRequest request) {
        // Get all users from database by ejb
        List<Users> allUsers = usersFacade.findAll();
        
        // List Entities -> List DTO
        List<AllUsersResDTO> data = new ArrayList<>();
        for (Users user : allUsers) {
            data.add(
                    AllUsersResDTO.builder()
                            .userID(user.getUserID())
                            .fullname(user.getFullName())
                            .address(user.getAddress())
                            .avatar(FileSupport.perfectImg(request, "users", user.getAvatar()))
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .role(user.getRole())
                            .gender(user.getGender())
                    .build()
            );
        }
        
        // Response Object
        DataResponse<List<AllUsersResDTO>> res = new DataResponse<>();
        
        res.setSuccess(true);
        res.setCode(200);
        res.setMessage("Successfully get all users");
        res.setData(data);
        
        return ResponseEntity.ok(res);
    }
    
    
    
    
    //02-01-xử lý api login
    @PostMapping("" + UrlProvider.Users.LOGIN)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req, BindingResult br, HttpServletRequest request, HttpServletResponse response) throws MethodArgumentNotValidException {
        if (br.hasErrors()) throw new MethodArgumentNotValidException(null, br);
        /**
         * Email, Password
         * B1: Find entity by username or email -> found -> B2
         * B2: check password of request and password of entity
         */
        Users user = usersFacade.findByEmail(req.getEmail());
        
        if (user == null || !passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            br.rejectValue("password", "error.password", "Email or password not correct");
        }
        
        if (br.hasErrors()) throw new MethodArgumentNotValidException(null, br);
        
        // INSERT JWT INTO COOKIE =======
        
        String accessToken = userTokenService.createAccessToken(user.getUserID() + "");
        String refreshToken = userTokenService.createRefreshToken(user.getUserID() + "");
        
        // Add AT and RT to cookie and set httpOnly to true
        Cookie accessTokenCookie = new Cookie(TokenNames.ACCESS_TOKEN.toString(), accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setMaxAge(60 * 60 * 24 * 365 * 60);
        accessTokenCookie.setPath("/");

        Cookie refreshTokenCookie = new Cookie(TokenNames.REFRESH_TOKEN.toString(), refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(60 * 60 * 24 * 365 * 60);
        refreshTokenCookie.setPath("/");

        // Add cookies to response
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
        
        LoginResponse res = new LoginResponse();
        res.setSuccess(true);
        res.setCode(200);
        res.setMessage("Successfully login");
        res.setUser(
                AllUsersResDTO.builder()
                            .userID(user.getUserID())
                            .fullname(user.getFullName())
                            .address(user.getAddress())
                            .avatar(FileSupport.perfectImg(request, "users", user.getAvatar()))
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .role(user.getRole())
                            .gender(user.getGender())
                    .build()
        );
        
        return ResponseEntity.ok(res);
    }

    
    
     //02-02 -api respone info data user(login thành công thì nó load info lên mục profile user: ảnh và tt khác)
    @GetMapping("" + UrlProvider.Users.GET_MY_DATA)
    public ResponseEntity<DataResponse<AllUsersResDTO>> getMyLoggedInData(HttpServletRequest request) {
       String userID = (String) request.getAttribute(RequestAttributeKeys.USER_ID.toString());
       
       DataResponse<AllUsersResDTO> res = new DataResponse<>();
       
       try {
           int userIDInt = Integer.parseInt(userID);
           
           Users user = usersFacade.find(userIDInt);
           
           AllUsersResDTO info = AllUsersResDTO.builder()
                   .userID(user.getUserID())
                            .fullname(user.getFullName())
                            .address(user.getAddress())
                            .avatar(FileSupport.perfectImg(request, "users", user.getAvatar()))
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .role(user.getRole())
                            .gender(user.getGender())
                    .build();
           
           
           res.setCode(200);
           res.setSuccess(true);
           res.setMessage("Successfully get your info");
           res.setData(info);
       }catch(NumberFormatException e){
           return ResponseEntity.badRequest().build();
       }
       
       return ResponseEntity.ok(res);
    }
    
    
    
    
   
    
    
    
    
    
    //03 store Users
    @PostMapping("" + UrlProvider.Users.STORE)
    public ResponseEntity<StandardResponse> store(
            @RequestPart(value = "image", required = true) MultipartFile image, // Upload image
            @RequestPart(value = "data", required = true) @Valid CreateUserReq req,
            BindingResult br,
            HttpSession session
    ) throws MethodArgumentNotValidException {
        //validation
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        if (image == null || image.isEmpty()) {
            br.rejectValue("image", "error.image", "Image is required");
        }
        if (usersFacade.findByEmail(req.getEmail()) != null) {
            br.rejectValue("email", "error.email", "Email is ready existing");
        }
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        //Add
        Users nUser = new Users();
        nUser.setFullName(req.getFullName());
        nUser.setPassword(passwordEncoder.encode(req.getPassword()));
        nUser.setAddress(req.getAddress());
        nUser.setPhone(req.getPhone());
        nUser.setEmail(req.getEmail());
        try {
            /**
             * Handle image
             */
            byte[] imageBytes = image.getBytes();
            String originFileName = image.getOriginalFilename();
            String newImageFileName = FileSupport.saveFile(session.getServletContext().getRealPath("/"), "users", imageBytes, originFileName);
            nUser.setAvatar(newImageFileName);
        } catch (IOException ex) {
            Logger.getLogger(ManageUsersApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nUser.setEmailVerifyCode(null);
        nUser.setForgotPasswordCode(null);
        nUser.setEmailVerifyAt(new Date());
        nUser.setRole(req.getRole());
        nUser.setGender(req.getGender());
        
        System.out.println("USER: " + nUser.toString());
        
        usersFacade.create(nUser);
        
        StandardResponse res = StandardResponse.builder()
             .success(true)
             .code(200)
             .message("Successfully create new user")
             .build();
        
        return ResponseEntity.ok(res);
    }
    
    
    
    
    
    
    
    
    //04 update
    @PostMapping("" + UrlProvider.Users.UPDATE)
    public ResponseEntity<StandardResponse> update(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "data", required = true) @Valid UpdateUsersReq req, 
            BindingResult br,
            HttpSession session
    ) throws MethodArgumentNotValidException {
        //validation
        if(br.hasErrors()) throw new MethodArgumentNotValidException(null, br);
          
        // Check user not existing?
        Users userToUpdate = usersFacade.find(req.getUserID());
        if (userToUpdate == null) {
            br.rejectValue("userID", "error.userID", "User ID is not found");
        }
        if (req.getPassword() != null && !req.getPassword().isEmpty() && req.getPassword().length() < 8) {
            br.rejectValue("password", "error.password", "Password must be greather or equal 8 characters");
        }
        if (userToUpdate != null && !userToUpdate.getEmail().equals(req.getEmail()) && usersFacade.findByEmail(req.getEmail()) != null) {
            br.rejectValue("email", "error.email", "Email is ready existing");
        }
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        //update
        userToUpdate.setFullName(req.getFullName());
        if (req.getPassword() != null && req.getPassword().length() >= 8) {
            userToUpdate.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        userToUpdate.setAddress(req.getAddress());
        userToUpdate.setPhone(req.getPhone());
        userToUpdate.setEmail(req.getEmail());
        if (image != null && !image.isEmpty()) {
            try {
                /**
                 * Handle image
                 */
                FileSupport.deleteFile(session.getServletContext().getRealPath("/"), "users", userToUpdate.getAvatar());

                byte[] imageBytes = image.getBytes();
                String originFileName = image.getOriginalFilename();
                String newImageFileName = FileSupport.saveFile(session.getServletContext().getRealPath("/"), "users", imageBytes, originFileName);
                userToUpdate.setAvatar(newImageFileName);
            } catch (IOException ex) {
                Logger.getLogger(ManageUsersApiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        userToUpdate.setRole(req.getRole());
        userToUpdate.setGender(req.getGender());
        
        System.out.println("USER: " + userToUpdate.toString());
        
        usersFacade.edit(userToUpdate);
        
        StandardResponse res = StandardResponse.builder()
             .success(true)
             .code(200)
             .message("Successfully update user")
             .build();
        
        return ResponseEntity.ok(res);
    }
    
    
    
    
    
    
    //05 delete
    @DeleteMapping(""+UrlProvider.Suppliers.DELETE)
    public ResponseEntity<StandardResponse> delete(@RequestParam("ids") String ids){
        String[] idArr = ids.split(",");

        for(String id : idArr){
            try {
                Users users = usersFacade.find(Integer.parseInt(id));
                if(users != null){
                    usersFacade.remove(users);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok(
                StandardResponse.builder()
                        .success(true)
                        .code(200)
                        .message("Successfully delete supplier")
                        .build()
        );
    }

    
    
   
    
  
    //insert localfacade cua session beans : (phim tat: lt + insert > call enterprise bean)
    private UsersFacadeLocal lookupUsersFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UsersFacadeLocal) c.lookup("java:global/CameraCantho24hApi/CameraCantho24hApi-ejb/UsersFacade!com.camera.session_beans.UsersFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
