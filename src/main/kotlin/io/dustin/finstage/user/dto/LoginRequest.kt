package io.dustin.finstage.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(

        @field:Schema(description = "사용자 이메일", example = "test@example.com")
        @field:Email(message = "이메일 형식이 아닙니다.")
        @field:NotBlank(message = "이메일은 필수입니다.")
        val email: String,

        @field:Schema(description = "비밀번호", example = "secure123")
        @field:NotBlank(message = "비밀번호는 필수입니다.")
        val password: String
)
