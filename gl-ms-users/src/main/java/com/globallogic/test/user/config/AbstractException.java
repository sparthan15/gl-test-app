package com.globallogic.test.user.config;

public abstract class AbstractException extends RuntimeException {
    public abstract int getErrorCode();
    protected AbstractException(String message){
        super(message);
    }
}
