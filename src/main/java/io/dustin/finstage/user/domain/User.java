package io.dustin.finstage.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final Long id;
    private final String email;
    private final String password;

    public static User create(String email, String rawPassword, PasswordEncoder encoder) {
        String encoded = encoder.encode(rawPassword);
        return new User(null, email, encoded);
    }

    public static User fromEntity(Long id, String email, String password) {
        return new User(id, email, password);
    }

    public boolean isPasswordMatch(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.password);
    }
}

