package com.camera.payload.requests.users;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserReq {
    
    @NotEmpty(message = "UsserName cannot empty")
    private String fullName;
    
    @Size(min = 8, message = "Password must be greather or equal 8 characters")
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
