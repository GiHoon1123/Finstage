package io.dustin.salesmgmt.authorization.domain;

import io.dustin.salesmgmt.common.exception.custom.InvalidPermissionException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 하나의 역할은 여러 권한을 가질 수 있다.
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Role {
    private final String roleName;
    private final List<Permission> permissions;

    public static Role of(String roleName, List<Permission> permissions) {
        validatePermissions(permissions);
        return new Role(roleName,permissions);
    }

    public Role updatePermissions(List<Permission> newPermissions) {
        validatePermissions(newPermissions);
        return new Role(this.roleName, newPermissions);
    }

    private static void validatePermissions(List<Permission> permissions) {
        Set<Permission> uniqueCheck = new HashSet<>(permissions);
        if (uniqueCheck.size() != permissions.size()) {
            throw new InvalidPermissionException("중복된 권한이 존재 합니다.");
        }

        if (permissions.contains(Permission.ADMIN) && permissions.size() > 1) {
            throw new InvalidPermissionException("Admin 권한 외 다른 권한은 포함될 수 없습니다.");
        }
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}
