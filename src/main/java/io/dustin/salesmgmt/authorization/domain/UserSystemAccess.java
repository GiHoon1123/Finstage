package io.dustin.salesmgmt.authorization.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// 유저의 역할과 권한 정보를 담는 최상위 도메인 엔티티
// 한 명의 유저는 하나의 역할을 가진다.
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSystemAccess {
    private final String userName;
    private final String userEmail;
    private final Role role;

    public static UserSystemAccess of(String userName, String userEmail, Role role){
        return new UserSystemAccess(userName,userEmail, role);
    }

    // UserSystemAccess는 외부에 역할 정보를 직접 노출하지 않고,
    // "이 유저가 어떤 권한을 갖고 있냐"는 질문에만 응답한다.
    public List<Permission> getPermissions() {
        return role.getPermissions();
    }

}
