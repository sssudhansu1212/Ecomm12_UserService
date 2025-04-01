package com.example.secondAPI.Exception;

public class UserAlreadyExsistsException extends Exception{

    public UserAlreadyExsistsException(String msg){
        super(msg);
    }
}
