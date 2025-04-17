package com.example.secondAPI.Security.Model;

import org.springframework.security.core.GrantedAuthority;
import com.example.secondAPI.Model.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority{

    private String authority;

    public CustomGrantedAuthority(){   
    }

    public CustomGrantedAuthority(Role roles){
        this.authority = roles.getName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }


}
