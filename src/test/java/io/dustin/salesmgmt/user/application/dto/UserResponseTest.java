package io.dustin.salesmgmt.user.application.dto;

import io.dustin.salesmgmt.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
public class UserResponseTest {

    @Test
    @DisplayName("도메인객체가 dto로 매핑되는지 확인한다.")
    void shouldConvertUserToResponse() {
        // given
        User user = User.of("dustin", "dustin@naver.com", "Temp Dept");

        // when
        UserResponse response = UserResponse.from(user);

        // then
        assertThat(response.displayName()).isEqualTo("dustin");
        assertThat(response.email()).isEqualTo("dustin@naver.com");
        assertThat(response.departmentName()).isEqualTo("Temp Dept");



    }
}
