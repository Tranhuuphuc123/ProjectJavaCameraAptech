package com.camera.payload.requests.users;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

//chuc nang update cho  trang users management
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUsersReq {
    
    @NotNull(message = "UserID cannot null")
    private Integer userID;
    
    @NotEmpty(message = "UsserName cannot empty")
    private String fullName;
    
    private String password;
    
    @NotEmpty(message = "address cannot empty")
    private String address;
    
    @NotEmpty(message = "Phone cannot empty")
    @Pattern(regexp = "[0-9]{10}", message = "Phone must be have 10 number")
    private String phone;
    
    @NotEmpty(message = "Email cannot empty")
    @Email(message = "Email not correct format")
    private String email;
    
    @NotEmpty(message = "Role cannot empty")
    private String role;
    
    @NotEmpty(message = "Gender cannot empty")
    private String gender;
    
    private String image;
}
