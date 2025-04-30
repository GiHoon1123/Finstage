package io.dustin.finstage.authorization.application.port.out;

import io.dustin.finstage.authorization.domain.UserCompanyAccess;

public interface RegisterUserCompanyAccessPort {

    void save(UserCompanyAccess userCompanyAccess);



}
