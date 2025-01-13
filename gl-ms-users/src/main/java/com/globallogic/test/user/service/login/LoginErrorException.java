package com.globallogic.test.user.service.login;

import com.globallogic.test.user.config.AbstractException;

public class LoginErrorException extends AbstractException {

    public LoginErrorException(){
        super("Username or password are incorrect");
    }

    @Override
    public int getErrorCode() {
        return LOGIN_ERROR_EXCEPTION_CODE;
    }
}
