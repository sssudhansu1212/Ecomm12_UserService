package com.example.secondAPI.DTO;

import java.util.List;

import com.example.secondAPI.Model.Role;
import com.example.secondAPI.Model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailDTO {

    private String name;
    private String email;
    private List<Role> roles;

    public static UserDetailDTO from(User user){
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.name = user.getName();
        userDetailDTO.email = user.getEmail();
        userDetailDTO.roles = user.getRoles();

        return userDetailDTO;
    }
}
