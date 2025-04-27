package io.dustin.salesmgmt.authorization.adapter.out.persistence;

import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;

import java.util.List;

public interface LoadUserCompanyAccessPort {

    boolean existsByUserMailAndCompanyIds(String userEmail, List<Long> companyIds);

    UserCompanyAccess findExistingCompanyAccesses(String userName, String userEmail, List<Long> companyIds);
}
