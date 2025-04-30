package io.dustin.finstage.authorization.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Company {
    private final Long companyId;
    private final String companyName;

    public static Company of(Long companyId, String companyName) {
        return new Company(companyId,companyName);
    }
}
