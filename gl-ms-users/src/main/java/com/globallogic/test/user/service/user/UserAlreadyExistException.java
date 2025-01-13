package com.globallogic.test.user.service.user;

import com.globallogic.test.user.config.AbstractException;

public class UserAlreadyExistException extends AbstractException {

    public UserAlreadyExistException(){
        super("User already exists with the given email");
    }

    @Override
    public int getErrorCode() {
        return USER_ALREADY_EXISTS_EXCEPTION_CODE;
    }
}
