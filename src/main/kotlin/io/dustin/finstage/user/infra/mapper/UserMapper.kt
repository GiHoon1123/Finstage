package io.dustin.finstage.user.infra.mapper

import io.dustin.finstage.user.domain.User
import io.dustin.finstage.user.infra.entity.UserEntity

object UserMapper {

    fun toDomain(entity: UserEntity): User? {
        return entity.id?.let {
            User.fromEntity(
                id = it,
                email = entity.email,
                password = entity.password
        )
        }
    }

    fun toEntity(domain: User): UserEntity {
        return UserEntity(
                id = domain.id,
                email = domain.email,
                password = domain.password
        )
    }
}
