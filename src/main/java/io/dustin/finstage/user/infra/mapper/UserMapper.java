package io.dustin.finstage.user.infra.mapper;

import io.dustin.finstage.user.domain.User;
import io.dustin.finstage.user.infra.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return User.fromEntity(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword()
        );
    }

    public static UserEntity toEntity(User domain) {
        return new UserEntity(
                domain.getId(),
                domain.getEmail(),
                domain.getPassword()
        );
    }
}
