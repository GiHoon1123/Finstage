package io.dustin.finstage.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("User.of()를 통해 도메인 객체를 생성할 수 있다.")
    void createUser(){
        // given
        String displayName = "dustin";
        String email = "dustin@naver.com";
        String department = "Development Team";

        // when
        User user = User.of(displayName, email, department);

        // then
        assertThat(user.getDisplayName()).isEqualTo(displayName);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getDepartmentName()).isEqualTo(department);

    }
}
