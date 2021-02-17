package com.andrew.common.crudboot.crudboot.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcHandler {

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handlerException (NoUserException noUserException){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(noUserException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity <UserIncorrectData> handlerException (Exception Exception){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(Exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
