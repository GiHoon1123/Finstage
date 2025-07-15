package io.dustin.finstage.user.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val email: String,

        val password: String

) {
    protected constructor() : this(null, "", "")
}
