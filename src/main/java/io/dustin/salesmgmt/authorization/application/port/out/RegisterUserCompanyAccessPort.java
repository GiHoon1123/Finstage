package io.dustin.salesmgmt.authorization.application.port.out;

import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;

public interface RegisterUserCompanyAccessPort {

    void save(UserCompanyAccess userCompanyAccess);



}
