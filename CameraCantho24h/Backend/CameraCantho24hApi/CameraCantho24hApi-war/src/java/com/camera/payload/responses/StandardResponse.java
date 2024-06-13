package com.camera.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Response Data chuản dữ liệu mà server trả về cho client

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponse {
    private boolean success;
    private int code;
    private String message;
}
