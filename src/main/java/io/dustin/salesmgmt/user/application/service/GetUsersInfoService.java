package io.dustin.salesmgmt.user.application.service;

import io.dustin.salesmgmt.common.annotation.UseCase;
import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;
import io.dustin.salesmgmt.user.application.port.in.GetUsersInfoUseCase;
import io.dustin.salesmgmt.user.application.port.in.query.GetUsersInfoQuery;
import io.dustin.salesmgmt.user.application.port.out.LoadUsersInfoPort;
import io.dustin.salesmgmt.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetUsersInfoService implements GetUsersInfoUseCase {

    private final LoadUsersInfoPort loadUserInfoPort;
    @Override
    public List<User> getUsersInfo(GetUsersInfoQuery query) {
        List<String> departmentsName = query.getDepartmentList();
        List<MsGraphUserInfo> getUsersInfo = loadUserInfoPort.getUsersInfoFromMsGraph(departmentsName);

        if (getUsersInfo == null || getUsersInfo.isEmpty()){
            throw new IllegalArgumentException("올바른 부서 정보를 입력해야 합니다.");
        }


        return getUsersInfo.stream()
                .flatMap(department -> department.members().stream()
                        .map(member -> User.of(
                                member.displayName(),
                                member.mail(),
                                department.name()
                        ))
                )
                .toList();
    }
}
