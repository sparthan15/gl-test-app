package com.globallogic.test.user.config;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int codigo;
    private String detail;
}
