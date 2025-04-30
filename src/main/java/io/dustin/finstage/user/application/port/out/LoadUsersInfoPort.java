package io.dustin.finstage.user.application.port.out;

import io.dustin.finstage.user.application.dto.MsGraphUserInfo;

import java.util.List;

public interface LoadUsersInfoPort {
    List<MsGraphUserInfo> getUsersInfoFromMsGraph(List<String> departmentsName);
}
