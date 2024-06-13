
package com.camera.dto.category;

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
public class AllCategoryResDTO {
    private Integer cateID;
    private String cateName;
    private String description;
    private String thumbNail;
    private String slug;
}
