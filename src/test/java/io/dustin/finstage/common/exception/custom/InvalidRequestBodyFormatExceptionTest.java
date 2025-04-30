package io.dustin.finstage.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class InvalidRequestBodyFormatExceptionTest extends RuntimeException {

    @Test
    @DisplayName("InvalidRequestBodyFormatException 생성 시 필드, 기대 타입, 거부된 값이 정확히 메시지로 나오는지 확인한다.")
    void shouldCreateInvalidRequestBodyFormatExceptionCorrectly() {
        // given
        String field = "companyId";
        String expectedType = "Long";
        String rejectedValue = "abc";

        // when
        InvalidRequestBodyFormatException exception = new InvalidRequestBodyFormatException(field, expectedType, rejectedValue);

        // then
        assertThat(exception.getField()).isEqualTo(field);
        assertThat(exception.getExpectedType()).isEqualTo(expectedType);
        assertThat(exception.getMessage())
                .isEqualTo("잘못된 타입 입력: 필드 'companyId'는 Long 타입이어야 합니다. (입력값: abc)");
    }
}
