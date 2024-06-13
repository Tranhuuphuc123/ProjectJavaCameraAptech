package com.camera.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// generic dữ liệu phản hồi -> 

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataResponse<T> extends StandardResponse {
    private T data;
}
