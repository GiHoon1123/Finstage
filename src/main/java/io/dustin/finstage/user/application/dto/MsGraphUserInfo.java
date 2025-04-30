package io.dustin.finstage.user.application.dto;

import java.util.List;

public record MsGraphUserInfo (
        String name, // 부서 이름
        List<Member> members
) {
    public record Member(
            String displayName,
            String mail
    ) {}
}

