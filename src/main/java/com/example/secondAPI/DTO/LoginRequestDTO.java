package com.example.secondAPI.DTO;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;

}
