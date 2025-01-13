package com.globallogic.test.user.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ErrorDetailTest {

    @Test
    void testErrorResponse() {
        ErrorResponse errorResponse =
                new ErrorResponse(ErrorDetail.builder()
                        .timestamp(LocalDateTime.now())
                        .code(AbstractException.LOGIN_ERROR_EXCEPTION_CODE)
                        .detail("some detail")
                        .build());
        Assertions.assertThat(errorResponse.getError().get("error")).isNotEmpty();
    }
}
