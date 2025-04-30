package io.dustin.finstage.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringOnlyValidator implements ConstraintValidator<StringOnly, String> {

    // 한글, 영문, 숫자, 공백만 허용
    private static final String REGEXP = "^[a-zA-Z가-힣0-9\\s]+$";
    private static final String ONLY_NUMBER_REGEXP = "^[0-9]+$"; // 숫자만

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // null/empty는 @NotEmpty에서 따로 검증할 거니까 통과
        }
        // 1. 정규식 매칭 실패 (허용된 문자만 와야 함)
        if (!value.matches(REGEXP)) {
            return false;
        }
        // 2. 숫자만 입력한 경우는 실패
        if (value.matches(ONLY_NUMBER_REGEXP)) {
            return false;
        }
        return true;
    }
}
