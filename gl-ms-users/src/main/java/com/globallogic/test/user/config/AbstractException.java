package com.globallogic.test.user.config;

public abstract class AbstractException extends RuntimeException {
    public static final int NOT_FOUND_EXCEPTION_CODE = 1;
    public static final int LOGIN_ERROR_EXCEPTION_CODE = 1;
    public static final int USER_ALREADY_EXISTS_EXCEPTION_CODE = 2;
    public abstract int getErrorCode();

    protected AbstractException(String message){
        super(message);
    }
}
