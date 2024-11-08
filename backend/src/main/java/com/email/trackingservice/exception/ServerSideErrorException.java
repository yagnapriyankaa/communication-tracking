package com.email.trackingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerSideErrorException extends RuntimeException {
    public ServerSideErrorException(String message) {
        super(message);
    }
}