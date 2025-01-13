package com.globallogic.test.user.config.password;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

class PasswordConstraintsValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        PasswordValidator passwordValidator = new PasswordValidator(
                Arrays.asList(
                        //Length rule. Min 10 max 128 characters
                        new LengthRule(8, 12),
                        //At most one Upper case
                        new MaxCharacterRule(CustomCharacterData.UPPER_CASE_EXCEEDED, 1),
                        //At most two number
                        new MaxCharacterRule(CustomCharacterData.DIGIT_EXCEEDED, 2),
                        new WhitespaceRule())
        );
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        //Sending one message each time failed validation.
        constraintValidatorContext.buildConstraintViolationWithTemplate(passwordValidator.getMessages(result).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
