package io.dustin.finstage.user.domain

import org.springframework.security.crypto.password.PasswordEncoder

data class User private constructor(
        val id: Long?,
        val email: String,
        val password: String
) {

    companion object {
        fun create(email: String, rawPassword: String, encoder: PasswordEncoder): User {
            val encoded = encoder.encode(rawPassword)
            return User(null, email, encoded)
        }

        fun fromEntity(id: Long, email: String, password: String): User {
            return User(id, email, password)
        }
    }

    fun isPasswordMatch(rawPassword: String, encoder: PasswordEncoder): Boolean {
        return encoder.matches(rawPassword, this.password)
    }
}
