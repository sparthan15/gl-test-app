package com.globallogic.test.user.service.user;

import com.globallogic.test.user.config.AbstractException;

public class UserNotFoundException extends AbstractException {

    public UserNotFoundException(){
        super("User does not exists. ");
    }

    @Override
    public int getErrorCode() {
        return NOT_FOUND_EXCEPTION_CODE;
    }
}
