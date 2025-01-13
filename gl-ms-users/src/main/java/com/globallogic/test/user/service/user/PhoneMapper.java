package com.globallogic.test.user.service.user;

import com.globallogic.test.user.controller.user.PhoneRequest;
import com.globallogic.test.user.persistence.Phone;
import org.springframework.stereotype.Component;

public interface PhoneMapper {
    Phone requestToEntity(PhoneRequest phoneRequest);

}

@Component
class PhoneMapperSimpleImpl implements PhoneMapper {
    public Phone requestToEntity(PhoneRequest phoneRequest) {
        return Phone.builder()
                .number(phoneRequest.getNumber())
                .cityCode(phoneRequest.getCityCode())
                .countryCode(phoneRequest.getCountryCode())
                .build();
    }
}
