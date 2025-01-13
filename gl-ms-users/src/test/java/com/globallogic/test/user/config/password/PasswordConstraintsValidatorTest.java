package com.globallogic.test.user.config.password;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasswordConstraintsValidatorTest {
    private  PasswordConstraintsValidator passwordConstraintsValidator = new PasswordConstraintsValidator();
    @Mock
    private ConstraintValidatorContext constraintValidatorContext;
    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;
    @Test
    void given_passwordHasOneUpperCase_and_lengthIsBetween8And12_then_isValid() {
        boolean result = passwordConstraintsValidator.isValid("A12aaaaaaa",
                constraintValidatorContext);
        Assertions.assertThat(result).isTrue();
        verify(constraintValidatorContext, never()).buildConstraintViolationWithTemplate(anyString());
        verify(constraintViolationBuilder, never()).addConstraintViolation();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A12aaaaaaabbb", "A12aaaa", "A12Aaaaaaa"})
    void given_passwordLenghtIsGreaterThan12_then_isInValid(String invalidPassword){
        setConstraintValidatorContext();
        boolean result = passwordConstraintsValidator.isValid(invalidPassword, constraintValidatorContext);
        Assertions.assertThat(result).isFalse();
        verifyConstraintViolationInvokation();
    }

    private void verifyConstraintViolationInvokation() {
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate(anyString());
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    private void setConstraintValidatorContext() {
        when(constraintViolationBuilder.addConstraintViolation()).thenReturn(constraintValidatorContext);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
    }

}
