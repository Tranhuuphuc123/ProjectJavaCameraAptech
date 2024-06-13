package com.camera.payload.request.categories;

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
public class CreateCategoryReq {
    @NotEmpty(message = "Category name cannot empty")
    private String cateName;
    
    @NotEmpty(message = "Description cannot empty")
    private String description;
}
