package io.dustin.salesmgmt.authorization.domain;

import io.dustin.salesmgmt.common.exception.custom.DuplicateCompanyAccessException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCompanyAccess {
    private final String userName;
    private final String userEmail;
    private final List<Company> companyAccess;

    public  static UserCompanyAccess of(String userName, String userEmail, List<Company> companyAccess) {
        validateNoDuplicateCompanies(companyAccess);
        return new UserCompanyAccess(userName,userEmail,companyAccess);
    }

    // 객체의 상태를 변경하지 않기 위해서 새로운 객체로 값을 리턴한다.
    // "회사 리스트만 바꾼다"는 행동을 잘 표현
    public UserCompanyAccess updateCompanyAccess(List<Company> newAccess) {
        validateNoDuplicateCompanies(newAccess);
        return new UserCompanyAccess(this.userName, this.userEmail, newAccess);
    }

    private static void validateNoDuplicateCompanies(List<Company> companies) {
        Map<Long, List<Company>> grouped = companies.stream()
                .collect(Collectors.groupingBy(Company::getCompanyId));

        List<String> duplicates = grouped.values().stream()
                .filter(list -> list.size() > 1)
                .map(list ->  list.getFirst().getCompanyName())
                .toList();

        if (!duplicates.isEmpty()){
            throw new DuplicateCompanyAccessException("중복된 회사가 존재합니다: " + String.join(", ", duplicates));
        }
    }
}
