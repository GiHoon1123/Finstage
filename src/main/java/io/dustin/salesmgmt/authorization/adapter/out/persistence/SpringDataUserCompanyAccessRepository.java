package io.dustin.salesmgmt.authorization.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserCompanyAccessRepository extends JpaRepository<UserCompanyAccessJpaEntity, Long> {

}

