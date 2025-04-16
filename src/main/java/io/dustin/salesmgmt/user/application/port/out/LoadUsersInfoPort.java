package io.dustin.salesmgmt.user.application.port.out;

import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;

import java.util.List;

public interface LoadUsersInfoPort {
    List<MsGraphUserInfo> getUsersInfoFromMsGraph(List<String> departmentsName);
}
