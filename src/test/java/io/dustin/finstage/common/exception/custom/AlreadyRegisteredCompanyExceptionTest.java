package io.dustin.finstage.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AlreadyRegisteredCompanyExceptionTest extends RuntimeException {

    @Test
    @DisplayName("테이블에 이미 존재하는 회사를 등록하려고 했을 때 올바른 예외를 던지는지 확인한다.")
    void shouldCreateExceptionWithMessage() {
        // given
        String errorMessage = "이미 등록된 회사입니다.";

        // when
        AlreadyRegisteredCompanyException exception = new AlreadyRegisteredCompanyException(errorMessage);

        // then
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);


    }
}
