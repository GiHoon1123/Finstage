package io.dustin.finstage.authorization.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserSystemAccessTest {

    @Test
    @DisplayName("유저가 가진 권한 목록이 Role로 부터 위임받았는지 확인한다.")
    void shouldReturnPermissionFromRole(){
        // given
        List<Permission> permissions = List.of(Permission.WRITE_USER, Permission.READ_ORDER);
        Role role = Role.of("테스트 롤",  permissions);

        // when
        List<Permission> result = role.getPermissions();

        // then
        assertThat(result).containsExactlyInAnyOrderElementsOf(permissions);
    }


    @Test
    @DisplayName("유저 시스템에 사용자 정보와 역할을 포함하는지 확인한다.")
    void shouldCreateUserSystemAccessCorrectly(){
        // given
        Role role = Role.of("Admin", List.of(Permission.ADMIN));
        UserSystemAccess access = UserSystemAccess.of("장득춘", "test@naver.com", role);

        // then
        assertThat(access.getUserName()).isEqualTo("장득춘");
        assertThat(access.getUserEmail()).isEqualTo("test@naver.com");
        assertThat(access.getPermissions()).containsExactly(Permission.ADMIN);


    }
}
