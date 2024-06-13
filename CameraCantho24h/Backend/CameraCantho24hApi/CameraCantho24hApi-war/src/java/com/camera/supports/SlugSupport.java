package com.camera.supports;

import org.springframework.stereotype.Component;

@Component
public class SlugSupport {
    public String generateSlug(String input) {
        // Bước 1: Chuyển đổi chuỗi thành chữ thường
        String output = input.toLowerCase();
        
        // Bước 2: Thay thế các dấu cách bằng dấu gạch nối
        output = output.replaceAll("\\s+", "-");

        // Bước 3: Loại bỏ các ký tự không hợp lệ
        output = output.replaceAll("[^a-z0-9-]", "");

        // Bước 4: Loại bỏ các dấu gạch nối thừa (nếu có)
        output = output.replaceAll("-+", "-");

        // Bước 5: Loại bỏ dấu gạch nối ở đầu và cuối chuỗi (nếu có)
        output = output.replaceAll("^-|-$", "");
        
        return output;
    }
}
