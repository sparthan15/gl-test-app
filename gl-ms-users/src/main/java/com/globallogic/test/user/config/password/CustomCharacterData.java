package com.globallogic.test.user.config.password;

import org.passay.CharacterData;
import org.passay.EnglishCharacterData;

public enum CustomCharacterData implements CharacterData {


    DIGIT_EXCEEDED("MAX_NUMBERS_EXCEEDED", "1234567890"),

    UPPER_CASE_EXCEEDED("MAX_UPPERCASE_EXCEEDED", EnglishCharacterData.UpperCase.getCharacters());

    private final String errorCode;

    /**
     * Characters.
     */
    private final String characters;


    /**
     * Creates a new english character data.
     *
     * @param code       Error code.
     * @param charString Characters as string.
     */
    CustomCharacterData(final String code, final String charString) {
        errorCode = code;
        characters = charString;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getCharacters() {
        return characters;
    }
}
