package io.dustin.finstage.user.application.port.in;

import io.dustin.finstage.user.application.port.in.query.GetUsersInfoQuery;
import io.dustin.finstage.user.domain.User;

import java.util.List;

public interface GetUsersInfoUseCase {
    List<User> getUsersInfo(GetUsersInfoQuery query);

}

