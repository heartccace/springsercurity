package com.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author heartccace
 * @create 2020-05-30 14:55
 * @Description TODO
 * @Version 1.0
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String message) {
        super(message);
    }
}
