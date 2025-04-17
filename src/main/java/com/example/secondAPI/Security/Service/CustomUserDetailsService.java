package com.example.secondAPI.Security.Service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.secondAPI.Model.User;
import com.example.secondAPI.Repository.UserRepository;
import com.example.secondAPI.Security.Model.CustomUserDetail;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(" User not found exception ");
        }
        CustomUserDetail customUserDetail = new CustomUserDetail(optionalUser.get());

        return customUserDetail;
    }

}
