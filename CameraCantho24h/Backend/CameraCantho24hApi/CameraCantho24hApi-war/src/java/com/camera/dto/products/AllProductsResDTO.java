
package com.camera.dto.products;

import com.camera.dto.category.AllCategoryResDTO;
import com.camera.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*class dto là môt class
=> data transfer object (là đối tượng chuyển đổi dữ liệu)
=> 
*/


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllProductsResDTO {
    private Integer productID;
    private String productName;
    private String description;
    private double unitPrice;
    private String avatar;
    private int quantity;
    private Double discount;
    private String warranty;
    private AllCategoryResDTO category;
}
