package com.globallogic.test.user.config.password;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomCharacterDataTest {

    @Test
    void testGetErrorCode(){
        Assertions.assertThat(CustomCharacterData.UPPER_CASE_EXCEEDED.getErrorCode()).isEqualTo("MAX_UPPERCASE_EXCEEDED");
    }
}
