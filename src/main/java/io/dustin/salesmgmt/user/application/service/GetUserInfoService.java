package io.dustin.salesmgmt.user.application.service;

import io.dustin.salesmgmt.user.application.port.in.GetUserInfoUseCase;
import io.dustin.salesmgmt.user.application.port.in.query.GetUserInfoQuery;
import io.dustin.salesmgmt.user.application.port.out.LoadUserInfoPort;
import io.dustin.salesmgmt.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetUserInfoService implements GetUserInfoUseCase {

    private final LoadUserInfoPort loadUserInfoPort;
    @Override
    public List<User> getUsers(GetUserInfoQuery query) {

        return null;
    }
}
