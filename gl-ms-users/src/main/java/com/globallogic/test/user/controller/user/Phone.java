package com.globallogic.test.user.controller.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Phone {
    private Long number;
    private Integer cityCode;
    private String countryCode;
}

