package io.dustin.finstage.user.application.dto;

import io.dustin.finstage.user.domain.User;

public record UserResponse(
        String displayName,
        String email,
        String departmentName
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getDisplayName(),
                user.getEmail(),
                user.getDepartmentName()
        );
    }
}
