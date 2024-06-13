
package com.camera.payload.request.products;

import com.camera.entities.Categories;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductsReq {
    @NotEmpty(message = "ProductName cannot empty")
    private String productName;
    
    @NotEmpty(message = "Description cannot empty")
    private String description;
    
    @NotEmpty(message = "UnitPrice cannot empty")
    private double unitPrice;
    
    private String avatar;
    
    @NotEmpty(message = "Quantity cannot empty")
    private int quantity;
    
    @NotNull(message = "discount cannot empty")
    private double discount;
    
    @NotEmpty(message = "warranty cannot empty")
    private String warranty;
    
    @NotNull(message = "cateID cannot empty")
    private int cateID;
}
