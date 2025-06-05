package io.dustin.finstage.user.service;

import io.dustin.finstage.common.annotation.UseCase;
import io.dustin.finstage.common.security.JwtTokenProvider;
import io.dustin.finstage.user.domain.User;
import io.dustin.finstage.user.dto.LoginRequest;
import io.dustin.finstage.user.dto.SignupRequest;
import io.dustin.finstage.user.dto.TokenResponse;
import io.dustin.finstage.user.infra.entity.UserEntity;
import io.dustin.finstage.user.infra.mapper.UserMapper;
import io.dustin.finstage.user.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signup(SignupRequest request) {
        // 이메일 중복 체크
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
                });

        // 도메인 유저 생성 (비밀번호 해싱 포함)
        User user = User.create(request.email(), request.password(), passwordEncoder);

        // 저장
        userRepository.save(UserMapper.toEntity(user));
    }

    public TokenResponse login(LoginRequest request) {
        // 이메일로 유저 조회
        UserEntity entity = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        User user = UserMapper.toDomain(entity);

        // 비밀번호 검증
        if (!user.isPasswordMatch(request.password(), passwordEncoder)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 토큰 발급
        String token = jwtTokenProvider.createToken(user.getEmail());
        return new TokenResponse(token);
    }
}
