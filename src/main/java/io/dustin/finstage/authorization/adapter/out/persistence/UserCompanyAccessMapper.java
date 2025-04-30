package io.dustin.finstage.authorization.adapter.out.persistence;

import io.dustin.finstage.authorization.domain.Company;
import io.dustin.finstage.authorization.domain.UserCompanyAccess;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserCompanyAccessMapper {

    public List<UserCompanyAccessJpaEntity> mapToJpaEntities(UserCompanyAccess userCompanyAccess) {
        return userCompanyAccess.getCompanyAccess().stream()
                .map(company -> mapToJpaEntity(userCompanyAccess.getUserEmail(), company))
                .toList();
    }

    public UserCompanyAccessJpaEntity mapToJpaEntity(String userEmail, Company company) {
        return new UserCompanyAccessJpaEntity(
                null,
                userEmail,
                company.getCompanyId(),
                company.getCompanyName(),
                LocalDateTime.now()
        );
    }

    public UserCompanyAccess mapToDomainEntity(String userName, String userEmail, List<UserCompanyAccessJpaEntity> entities) {
        List<Company> companyAccess = entities.stream()
                .map(entity -> Company.of(entity.getCompanyId(), entity.getCompanyName()))
                .toList();

        return UserCompanyAccess.of(userName, userEmail, companyAccess);
    }
}
