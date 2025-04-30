package io.dustin.finstage.common.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringOnlyValidatorTest {


    private final StringOnlyValidator validator = new StringOnlyValidator();


    @Test
    @DisplayName("허용된 문자(한글, 영문, 숫자, 공백) 조합은 통과한다.")
    void validStringShouldPass() {
        assertThat(validator.isValid("Test1123 한국어", null)).isTrue();
        assertThat(validator.isValid("한국어1123 영어", null)).isTrue();
    }

    @Test
    @DisplayName("숫자만 입력하면 실패한다.")
    void onlyNumbersShouldFail() {
        assertThat(validator.isValid("123456", null)).isFalse();
    }


    @Test
    @DisplayName("특수문자가 포함되면 실패한다.")
    void specialCharactersShouldFail(){
        assertThat(validator.isValid("test@!@#",null)).isFalse();
        assertThat(validator.isValid("안녕@!@#하세요",null)).isFalse();
    }

    @Test
    @DisplayName("공백은 NotEmpty 어노테이션이 처리하기 때문에 통과한다")
    void nullOrEmptyShouldPass() {
        assertThat(validator.isValid(null,null)).isTrue();
        assertThat(validator.isValid("",null)).isTrue();
    }
}
