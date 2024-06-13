
package com.camera.payload.requests.suppliers;

import javax.validation.constraints.Pattern;
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
public class CreateSupplierReq {
    @NotEmpty(message = "Suplier name cannot empty")
    private String supplierName;
    
    @NotEmpty(message = "Phone cannot empty")
    @Pattern(regexp = "[0-9]{10}", message = "Phone must be have 10 number")
    private String phone;
    
    @NotEmpty(message = "Email cannot empty")
    @Email(message = "Email not correct format")
    private String email;
    
    @NotEmpty(message = "Description cannot empty")
    private String description;
}
