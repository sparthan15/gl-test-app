package com.globallogic.test.user.config.password;

import org.passay.CharacterData;
import org.passay.*;

import java.util.LinkedHashMap;
import java.util.Map;

class MaxCharacterRule implements Rule {
    protected int maxNumberAppearances;
    protected final CharacterData characterData;

    public MaxCharacterRule(CharacterData characterData, int num) {
        this.characterData = characterData;
        this.setNumberOfCharacters(num);
    }

    public void setNumberOfCharacters(int n) {
        if (n > 0) {
            this.maxNumberAppearances = n;
        } else {
            throw new IllegalArgumentException("argument must be greater than zero");
        }
    }

    public int getNumberOfCharacters() {
        return this.maxNumberAppearances;
    }

    public RuleResult validate(PasswordData passwordData) {
        String repeated = PasswordUtils.getMatchingCharacters(
                String.valueOf(characterData.getCharacters()),
                passwordData.getPassword(),
                maxNumberAppearances + 1);

        return repeated.length() > this.maxNumberAppearances ? new RuleResult(false,
                new RuleResultDetail(this.characterData.getErrorCode(),
                        this.createRuleResultDetailParameters(repeated)),
                this.createRuleResultMetadata(passwordData)) : new RuleResult(true,
                this.createRuleResultMetadata(passwordData));
    }

    protected Map<String, Object> createRuleResultDetailParameters(String matchingChars) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("minimumRequired", this.maxNumberAppearances);
        m.put("matchingCharacterCount", matchingChars.length());
        m.put("validCharacters", String.valueOf(this.characterData.getCharacters()));
        m.put("matchingCharacters", matchingChars);
        return m;
    }

    protected RuleResultMetadata createRuleResultMetadata(PasswordData password) {
        try {
            return new RuleResultMetadata(RuleResultMetadata.CountCategory.valueOf(this.characterData.toString()), PasswordUtils.countMatchingCharacters(this.characterData.getCharacters(), password.getPassword()));
        } catch (IllegalArgumentException var3) {
            return new RuleResultMetadata();
        }
    }

}
