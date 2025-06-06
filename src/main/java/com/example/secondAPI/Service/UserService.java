package com.example.secondAPI.Service;

import java.util.List;

import com.example.secondAPI.Exception.InvalidTokenException;
import com.example.secondAPI.Exception.UserAlreadyExsistsException;
import com.example.secondAPI.Exception.UserNameNotFoundException;
import com.example.secondAPI.Model.Role;
import com.example.secondAPI.Model.Token;
import com.example.secondAPI.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {

    public User SignUp(String name,String email,String pwd,List<Role> role)throws UserAlreadyExsistsException,JsonProcessingException;
    public Token login(String email, String password)throws UserNameNotFoundException;
    public String signOut(String tokenValue)throws InvalidTokenException;
    public User validateToken(String tokenVal)throws InvalidTokenException;
} 
