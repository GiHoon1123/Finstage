package io.dustin.finstage.authorization.domain;

import io.dustin.finstage.common.exception.custom.InvalidPermissionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RoleTest {

    @Test
    @DisplayName("역할에 할당된 유효한 권한들이 출력되는지 확인한다.")
    void shouldCreateRoleWithValidPermission(){
        // given
        List<Permission> permissions = List.of(Permission.READ_ORDER, Permission.WRITE_ORDER);

        // when
        Role role = Role.of("Manager", permissions);

        // then
        assertThat(role.getRoleName()).isEqualTo("Manager");
        assertThat(role.getPermissions()).containsExactlyInAnyOrder(Permission.READ_ORDER,Permission.WRITE_ORDER);
    }

    @Test
    @DisplayName("중복된 권한을 할당하려 할 때 에러가 발생하는지 확인한다.")
    void shouldThrowException_whenDuplicatePermissionsProvided(){
        // given
        List<Permission> duplicated = List.of(Permission.READ_ORDER, Permission.READ_ORDER);

        // when & then
        assertThatThrownBy(() -> Role.of("DuplicatedRole", duplicated))
                .isInstanceOf(InvalidPermissionException.class);

    }

    @Test
    @DisplayName("Admin 권한외에 다른 권한이 존재할때, 예외가 발생하는지 확인한다.")
    void shouldThrowException_whenAdminMixedWithOtherPermissions(){
        // given
        List<Permission> invalid = List.of(Permission.ADMIN, Permission.WRITE_ORDER);

        // when & then
        assertThatThrownBy(()-> Role.of("InvalidAdminMix", invalid))
                .isInstanceOf(InvalidPermissionException.class);

    }

    @Test
    @DisplayName("Rold에 대해 권한의 업데이트가 정상적으로 처리되는지 확인한다.")
    void shouldUpdatePermissionsSuccessfully(){
        // given
        Role role = Role.of("Update", List.of(Permission.READ_USER));
        List<Permission> newPermission = List.of(Permission.WRITE_USER);

        // when
        Role updateRole = role.updatePermissions(newPermission);

        // then
        assertThat(updateRole.getPermissions()).containsExactly(Permission.WRITE_USER);


    }




}
