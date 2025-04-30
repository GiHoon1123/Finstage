package io.dustin.finstage.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class DuplicateCompanyAccessExceptionTest extends  RuntimeException {
    @Test
    @DisplayName("클라이언트가 중복된 회사를 입력했을 때 예외가 발생하는지 확인한다.")
    void shouldCreateExceptionWithMessage() {

        // given
        String errorMessage = "중복된 회사가 존재합니다.";

        // when
        DuplicateCompanyAccessException exception = new DuplicateCompanyAccessException(errorMessage);

        // then
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);

    }




}
