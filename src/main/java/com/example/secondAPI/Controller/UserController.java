package com.example.secondAPI.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.secondAPI.DTO.LoginRequestDTO;
import com.example.secondAPI.DTO.SignUpRequestDTO;
import com.example.secondAPI.DTO.TokenResponseDTO;
import com.example.secondAPI.DTO.UserDetailDTO;
import com.example.secondAPI.DTO.UserResponseDTO;
import com.example.secondAPI.Exception.InvalidTokenException;
import com.example.secondAPI.Exception.UserAlreadyExsistsException;
import com.example.secondAPI.Exception.UserNameNotFoundException;
import com.example.secondAPI.Model.Token;
import com.example.secondAPI.Model.User;
import com.example.secondAPI.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.common.lang.NonNull;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public UserResponseDTO signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)throws UserAlreadyExsistsException,JsonProcessingException{
        User user = userService.SignUp(signUpRequestDTO.getName(), signUpRequestDTO.getEmail()
                                        ,signUpRequestDTO.getPassword() , signUpRequestDTO.getRoles());
        
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setMsg(" User has been successfully saved !! ");
                                        
        return userResponseDTO;
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO)throws UserNameNotFoundException{
        Token token = userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setValue(token.getValue());
        return tokenResponseDTO;
    }

    @PostMapping("/signOut/{tokenValue}")
    public ResponseEntity<String> signOut(@PathVariable("tokenValue") String tokenValue)throws InvalidTokenException{
        String returnredValue = userService.signOut(tokenValue);

        return new ResponseEntity<>(returnredValue,HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<UserDetailDTO> validateToken(@PathVariable("token") @NonNull String token)throws InvalidTokenException{
        User returnedUserFromToken = userService.validateToken(token);
        return new ResponseEntity<>(UserDetailDTO.from(returnedUserFromToken),HttpStatus.ACCEPTED);
    }
 
}
