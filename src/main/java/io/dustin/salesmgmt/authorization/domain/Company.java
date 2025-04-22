package io.dustin.salesmgmt.authorization.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Company {
    private final Long companyId;
    private final String companyName;

    public static Company of(Long companyId, String companyName) {
        return new Company(companyId,companyName);
    }
}
