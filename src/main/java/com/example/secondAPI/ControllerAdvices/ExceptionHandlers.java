package com.example.secondAPI.ControllerAdvices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.secondAPI.DTO.ExceptionDTO;
import com.example.secondAPI.Exception.InvalidTokenException;
import com.example.secondAPI.Exception.UserAlreadyExsistsException;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserAlreadyExsistsException.class)
    public ResponseEntity<ExceptionDTO> handleUserExsistsException(UserAlreadyExsistsException userAlreadyExsistsException){
        ExceptionDTO exceptiondto = new ExceptionDTO();
        exceptiondto.setMessage(userAlreadyExsistsException.getMessage());
        exceptiondto.setErrorCode(400);
        return new ResponseEntity<>(exceptiondto,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionDTO> invalidTokenException(InvalidTokenException invalidTokenException){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(invalidTokenException.getMessage());
        exceptionDTO.setErrorCode(400);
        return new ResponseEntity<>(exceptionDTO,HttpStatus.BAD_GATEWAY);
    }
}
