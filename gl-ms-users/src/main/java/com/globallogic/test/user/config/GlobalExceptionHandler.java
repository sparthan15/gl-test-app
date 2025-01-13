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
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR = "error";

    @ExceptionHandler({UserNotFoundException.class})
    public final ResponseEntity<Map<String, List<ErrorResponse>>>  handleException(Exception ex,
                                                                            WebRequest request) {
        AbstractException exception = (UserNotFoundException) ex;
        return new ResponseEntity<>(Map.of(ERROR, List.of(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .codigo(exception.getErrorCode())
                .detail(exception.getMessage())
                .build())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({LoginErrorException.class})
    public final ResponseEntity<Map<String, List<ErrorResponse>>> handleLoginException(Exception ex,
                                                                        WebRequest request) {
        AbstractException exception = (LoginErrorException) ex;
        return new ResponseEntity<>(Map.of(ERROR, List.of(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .codigo(exception.getErrorCode())
                .detail(exception.getMessage())
                .build())), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public final ResponseEntity<Map<String, List<ErrorResponse>>> handleUserAlreadyExistsException(Exception ex,
                                                                                WebRequest request) {
        AbstractException exception = (UserAlreadyExistException) ex;
        return new ResponseEntity<>(Map.of(ERROR, List.of(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .codigo(exception.getErrorCode())
                .detail(exception.getMessage())
                .build())), HttpStatus.CONFLICT);
    }
}
