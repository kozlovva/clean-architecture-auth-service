package ru.foodtechlab.callcenter.auth.config.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationApiException extends AuthenticationException {

    public AuthenticationApiException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
