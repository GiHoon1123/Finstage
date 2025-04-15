package io.dustin.salesmgmt.user.application.port.in;

import io.dustin.salesmgmt.user.application.port.in.query.GetUserInfoQuery;
import io.dustin.salesmgmt.user.domain.User;

import java.util.List;

public interface GetUserInfoUseCase {
    List<User> getUsers(GetUserInfoQuery query);

}

