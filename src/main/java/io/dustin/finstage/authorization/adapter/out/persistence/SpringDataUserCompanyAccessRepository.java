package io.dustin.finstage.authorization.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataUserCompanyAccessRepository extends JpaRepository<UserCompanyAccessJpaEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
            "FROM UserCompanyAccessJpaEntity u " +
            "WHERE u.userEmail = :userEmail AND u.companyId IN (:companyIds)")
    boolean existsByUserEmailAndCompanyIdIn(String userEmail, List<Long> companyIds);

    List<UserCompanyAccessJpaEntity> findByUserEmailAndCompanyIdIn(String userEmail, List<Long> companyIds);

}

