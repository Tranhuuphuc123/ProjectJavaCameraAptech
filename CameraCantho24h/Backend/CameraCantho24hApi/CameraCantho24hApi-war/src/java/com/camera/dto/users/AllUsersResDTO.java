package com.camera.dto.users;

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
public class AllUsersResDTO {
    private int userID;
    private String fullname;
    private String avatar;
    private String phone;
    private String email;
    private String address;
    private String gender;
    private String role;
}
