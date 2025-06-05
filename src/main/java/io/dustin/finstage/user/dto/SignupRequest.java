package io.dustin.finstage.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(

        @Schema(description = "사용자 이메일", example = "test@example.com")
        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 필수입니다.")
        String email,

        @Schema(description = "6자 이상 비밀번호", example = "secure123")
        @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다.")
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password

) {}
