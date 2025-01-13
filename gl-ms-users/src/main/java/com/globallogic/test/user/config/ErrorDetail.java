package com.globallogic.test.user.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorDetail {
    private LocalDateTime timestamp;
    private int code;
    private String detail;

}
