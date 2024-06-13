package com.camera.payload.responses;

import com.camera.dto.users.AllUsersResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends StandardResponse {
    private AllUsersResDTO user;
}
