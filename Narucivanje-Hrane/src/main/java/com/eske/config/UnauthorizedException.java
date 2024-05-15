package com.eske.config;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {


    public UnauthorizedException(String mess)
    {
        super(mess);
    }
}
