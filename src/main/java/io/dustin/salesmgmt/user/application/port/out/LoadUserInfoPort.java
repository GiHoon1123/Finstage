package io.dustin.salesmgmt.user.application.port.out;

import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;

public interface LoadUserInfoPort {
    MsGraphUserInfo getUserInfoFromMsGraph(String department);
}
