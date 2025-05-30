package io.dustin.finstage.authorization.adapter.out.persistence;

import io.dustin.finstage.authorization.application.port.out.RegisterUserCompanyAccessPort;
import io.dustin.finstage.authorization.domain.UserCompanyAccess;
import io.dustin.finstage.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserCompanyAccessAdapter implements RegisterUserCompanyAccessPort, LoadUserCompanyAccessPort {
    private final UserCompanyAccessMapper mapper;
    private final SpringDataUserCompanyAccessRepository repository;

    @Override
    public void save(UserCompanyAccess userCompanyAccess) {

        List<UserCompanyAccessJpaEntity> entities = mapper.mapToJpaEntities(userCompanyAccess);
        repository.saveAll(entities);
    }

    @Override
    public UserCompanyAccess findExistingCompanyAccesses(String userName, String userEmail, List<Long> companyIds) {
        List<UserCompanyAccessJpaEntity> entities = repository.findByUserEmailAndCompanyIdIn(userEmail, companyIds);
        return mapper.mapToDomainEntity(userName, userEmail, entities);
    }

    @Override
    public boolean existsByUserMailAndCompanyIds(String userEmail, List<Long> companyIds) {
        return repository.existsByUserEmailAndCompanyIdIn(userEmail, companyIds);
    }
}
