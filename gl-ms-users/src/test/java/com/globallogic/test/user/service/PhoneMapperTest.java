package com.globallogic.test.user.service;

import com.globallogic.test.user.TestUtil;
import com.globallogic.test.user.persistence.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PhoneMapperTest {
    @InjectMocks
    private PhoneMapperSimpleImpl phoneMapperSimple;

    @Test
    void testRequestToEntity(){
        Phone toEntity = phoneMapperSimple.requestToEntity(TestUtil.phoneRequest);
        assertThat(toEntity).isNotNull();
        assertThat(toEntity.getCityCode()).isEqualTo(TestUtil.phoneRequest.getCityCode());
        assertThat(toEntity.getNumber()).isEqualTo(TestUtil.phoneRequest.getNumber());
        assertThat(toEntity.getCountryCode()).isEqualTo(TestUtil.phoneRequest.getCountryCode());
    }
}
