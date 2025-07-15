package io.dustin.finstage.common.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class StringOnlyValidator : ConstraintValidator<StringOnly, String> {

    companion object {
        // 한글, 영문, 숫자, 공백만 허용
        private val REGEXP = Regex("^[a-zA-Z가-힣0-9\\s]+$")
        private val ONLY_NUMBER_REGEXP = Regex("^[0-9]+$")
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) {
            return true // null/empty는 @NotEmpty 등에서 따로 검증
        }

        if (!REGEXP.matches(value)) {
            return false
        }

        if (ONLY_NUMBER_REGEXP.matches(value)) {
            return false
        }

        return true
    }
}
