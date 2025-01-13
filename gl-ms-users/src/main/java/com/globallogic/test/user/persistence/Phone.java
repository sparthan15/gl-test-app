package com.globallogic.test.user.persistence;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "phones")
@Builder
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private Long number;
    private Integer cityCode;
    private String countryCode;
}
