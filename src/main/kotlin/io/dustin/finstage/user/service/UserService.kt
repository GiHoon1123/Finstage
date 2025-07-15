package io.dustin.finstage.user.service

import io.dustin.finstage.common.annotation.UseCase
import io.dustin.finstage.common.security.JwtTokenProvider
import io.dustin.finstage.user.domain.User
import io.dustin.finstage.user.dto.LoginRequest
import io.dustin.finstage.user.dto.SignupRequest
import io.dustin.finstage.user.dto.TokenResponse
import io.dustin.finstage.user.infra.mapper.UserMapper
import io.dustin.finstage.user.infra.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder

@UseCase
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtTokenProvider: JwtTokenProvider
) {

    fun signup(request: SignupRequest) {
        // 이메일 중복 체크
        userRepository.findByEmail(request.email).ifPresent {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다.")
        }

        // 도메인 유저 생성 (비밀번호 해싱 포함)
        val user = User.create(request.email, request.password, passwordEncoder)

        // 저장
        userRepository.save(UserMapper.toEntity(user))
    }

    fun login(request: LoginRequest): TokenResponse? {
        // 이메일로 유저 조회
        val entity = userRepository.findByEmail(request.email)
                .orElseThrow { IllegalArgumentException("존재하지 않는 사용자입니다.") }

        val user = UserMapper.toDomain(entity)

        // 비밀번호 검증
        requireNotNull(user) { "존재하지 않는 사용자입니다." }

        require(user.isPasswordMatch(request.password, passwordEncoder)) {
            "비밀번호가 일치하지 않습니다."
        }


        // 토큰 발급
        val token = user?.let { jwtTokenProvider.createToken(it.email) }
        return token?.let { TokenResponse(it) }
    }
}
