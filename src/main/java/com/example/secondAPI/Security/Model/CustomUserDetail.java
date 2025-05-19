package com.example.secondAPI.Security.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.secondAPI.Model.Role;
import com.example.secondAPI.Model.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class CustomUserDetail implements UserDetails{

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public CustomUserDetail(){
    }

    public CustomUserDetail(User user){
        this.username = user.getEmail();
        this.password = user.getPassword();

        List<GrantedAuthority> customGrantedAuthorityList = new ArrayList<>();
        for(Role role : user.getRoles()){
           customGrantedAuthorityList.add(new CustomGrantedAuthority(role));
        }

        this.authorities = customGrantedAuthorityList;
        this.userId = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
