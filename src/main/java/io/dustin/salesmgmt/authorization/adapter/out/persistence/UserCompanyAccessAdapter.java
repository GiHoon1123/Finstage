package io.dustin.salesmgmt.authorization.adapter.out.persistence;

import io.dustin.salesmgmt.authorization.application.port.out.RegisterUserCompanyAccessPort;
import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;
import io.dustin.salesmgmt.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserCompanyAccessAdapter implements RegisterUserCompanyAccessPort {
    private final UserCompanyAccessMapper mapper;
    private final SpringDataUserCompanyAccessRepository repository;

    @Override
    public void save(UserCompanyAccess userCompanyAccess) {

        List<UserCompanyAccessJpaEntity> entities = mapper.mapToJpaEntities(userCompanyAccess);
        repository.saveAll(entities);
    }
}
