package io.dustin.salesmgmt.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringOnlyValidator implements ConstraintValidator<StringOnly, String> {

    private static final String REGEXP = "^[a-zA-Z가-힣\\s]+$"; // 한글, 영문, 공백만 허용

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // null/empty는 @NotEmpty 따로 검증할 거니까 통과시킴
        }
        return value.matches(REGEXP);
    }
}
