package io.dustin.salesmgmt.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class InvalidPermissionExceptionTest extends RuntimeException {

    @Test
    @DisplayName("유효하지 않은 권한을 입력했을 때 올바른 예외를 던지는지 확인한다.")
    void shouldCreateExceptionWithMessage() {
        // given
        String errorMessage = "유효하지 않은 권한입니다.";

        // when
        InvalidPermissionException exception = new InvalidPermissionException(errorMessage);

        // then
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);





    }
}
