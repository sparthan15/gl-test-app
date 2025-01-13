package com.globallogic.test.user.config;

import com.globallogic.test.user.service.login.LoginErrorException;
import com.globallogic.test.user.service.user.UserAlreadyExistException;
import com.globallogic.test.user.service.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex,
                                                               WebRequest request) {
        AbstractException exception = (UserNotFoundException) ex;
        ErrorResponse.ErrorDetail errorDetail = ErrorResponse.ErrorDetail.builder()
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
        ErrorResponse.ErrorDetail errorDetail = ErrorResponse.ErrorDetail.builder()
                .timestamp(LocalDateTime.now())
                .code(exception.getErrorCode())
                .detail(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ErrorResponse(errorDetail), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex) {

        AccessDeniedException exception = (AccessDeniedException) ex;
        ErrorResponse.ErrorDetail errorDetail = ErrorResponse.ErrorDetail.builder()
                .timestamp(LocalDateTime.now())
                .code(0)
                .detail(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(errorDetail));
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public final ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(Exception ex,
                                                                                WebRequest request) {
        AbstractException exception = (UserAlreadyExistException) ex;
        ErrorResponse.ErrorDetail errorDetail = ErrorResponse.ErrorDetail.builder()
                .timestamp(LocalDateTime.now())
                .code(exception.getErrorCode())
                .detail(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ErrorResponse(errorDetail), HttpStatus.CONFLICT);
    }

}
