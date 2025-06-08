package io.dustin.finstage.user.controller;

import io.dustin.finstage.common.response.CommonResponse;

import io.dustin.finstage.user.dto.LoginRequest;
import io.dustin.finstage.user.dto.SignupRequest;
import io.dustin.finstage.user.dto.TokenResponse;
import io.dustin.finstage.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "사용자 인증 API")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(
            summary = "회원가입",
            description = "이메일과 비밀번호를 입력하여 회원가입합니다."
    )
    public CommonResponse<Void> signup(@Valid @RequestBody SignupRequest request) {
        userService.signup(request);
        return CommonResponse.success();
    }

    @PostMapping("/login")
    @Operation(
            summary = "로그인",
            description = "이메일과 비밀번호로 로그인하여 JWT 토큰을 발급받습니다."
    )
    public CommonResponse<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        TokenResponse response = userService.login(request);
        return CommonResponse.success(response);
    }
}
