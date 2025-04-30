package io.dustin.finstage.user.adapter.out.msgraph;

import io.dustin.finstage.common.annotation.GraphApiAdapter;
import io.dustin.finstage.user.application.dto.MsGraphUserInfo;
import io.dustin.finstage.user.application.port.out.LoadUsersInfoPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@GraphApiAdapter
@RequiredArgsConstructor
public class LoadUsersInfoFromMsGraphAdapter implements LoadUsersInfoPort {

    // Microsoft Graph api를 사용하여  Azure 구독을 조회해 유저 정보를 가져오는 부분을 Mock으로 대체함.
    private  final MockMsGraphUserClient mockMsGraphUserClient;



    @Override
    public List<MsGraphUserInfo> getUsersInfoFromMsGraph(List<String> departmentsName) {
        if (departmentsName == null || departmentsName.isEmpty()){
            return  mockMsGraphUserClient.getAllDepartments();
        }
        return mockMsGraphUserClient.getDepartmentsByName(departmentsName);
    }
}
