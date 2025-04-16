package io.dustin.salesmgmt.user.application.port.in;

import io.dustin.salesmgmt.user.application.port.in.query.GetUsersInfoQuery;
import io.dustin.salesmgmt.user.domain.User;

import java.util.List;

public interface GetUsersInfoUseCase {
    List<User> getUsersInfo(GetUsersInfoQuery query);

}

