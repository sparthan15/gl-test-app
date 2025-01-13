package com.globallogic.test.user.config;

import com.globallogic.test.user.service.login.LoginErrorException;
import com.globallogic.test.user.service.user.UserAlreadyExistException;
import com.globallogic.test.user.service.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleException(Exception ex,
                                                               WebRequest request) {
        AbstractException exception = (UserNotFoundException) ex;
        ErrorDetail errorDetail = ErrorDetail.builder()
                .timestamp(LocalDateTime.now())
                .code(exception.getErrorCode())
                .detail(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ErrorResponse(errorDetail), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({LoginErrorException.class})
    public final ResponseEntity<ErrorResponse> handleLoginException(Exception ex,
                                                                    WebRequest request) {
        AbstractException exception = (LoginErrorException) ex;
        ErrorDetail errorDetail = ErrorDetail.builder()
                .timestamp(LocalDateTime.now())
                .code(exception.getErrorCode())
                .detail(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ErrorResponse(errorDetail), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public final ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(Exception ex,
                                                                                WebRequest request) {
        AbstractException exception = (UserAlreadyExistException) ex;
        ErrorDetail errorDetail = ErrorDetail.builder()
                .timestamp(LocalDateTime.now())
                .code(exception.getErrorCode())
                .detail(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ErrorResponse(errorDetail), HttpStatus.CONFLICT);
    }
}
