package com.scorer.clientPhone._Excptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "TOKEN_FAILED")
public class UserH5AdminCookieTimeOutException extends Exception{
    public UserH5AdminCookieTimeOutException() {
    }
    public UserH5AdminCookieTimeOutException(String message) {
        super(message);
    }
}
