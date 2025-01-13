package com.globallogic.test.user.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ErrorResponse {
    static final String FIELD_NAME = "error";
    private Map<String, List<ErrorDetail>> error;

    public ErrorResponse(ErrorDetail errorDetail) {
        this.error = new HashMap<>();
        this.error.put(FIELD_NAME, List.of(errorDetail));
    }

    public ErrorDetail getErrorDetail() {
        return this.error.get(FIELD_NAME).get(0);
    }

}
