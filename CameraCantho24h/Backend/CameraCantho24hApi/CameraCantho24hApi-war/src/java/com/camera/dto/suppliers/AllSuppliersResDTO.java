
package com.camera.dto.suppliers;

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
public class AllSuppliersResDTO {
    private Integer supplierID;
    private String supplierName;
    private String phone;
    private String email;
    private String description;
}
