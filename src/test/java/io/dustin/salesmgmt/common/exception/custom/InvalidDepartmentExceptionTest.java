package io.dustin.salesmgmt.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class InvalidDepartmentExceptionTest extends RuntimeException {

    @Test
    @DisplayName("부서명이 잘못되었을대 올바른 예외를 던지는지 확인한다.")
    void shouldCreateExceptionWithMessage() {
        // given
        String errorMessage = "부서명이 잘못되었습니다.";

        // when
        InvalidDepartmentException exception = new InvalidDepartmentException(errorMessage);

        // then
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }
}
