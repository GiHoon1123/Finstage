package io.dustin.salesmgmt.authorization.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PermissionTest {

    @Test
    @DisplayName("올바른 키와 값을 가지고 있는지 확인한다.")
    void shouldHaveCorrectKeyAndDescriptionForEachPermission(){
        assertThat(Permission.READ_ORDER.getKey()).isEqualTo("readOrder");
        assertThat(Permission.READ_ORDER.getDescription()).isEqualTo("주문 조회 권한");

    }

    @Test
    @DisplayName("상수 집합 클래스의 총 크기를 확인한다.")
    void shouldContainAllDefinedPermission(){
        assertThat(Permission.values()).hasSize(7);
    }
}
