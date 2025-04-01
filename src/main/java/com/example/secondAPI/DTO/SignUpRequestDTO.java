package com.example.secondAPI.DTO;

import java.util.List;

import com.example.secondAPI.Model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {

    private String name;
    private String email;
    private String password;
    private List<Role> roles;
    
}
