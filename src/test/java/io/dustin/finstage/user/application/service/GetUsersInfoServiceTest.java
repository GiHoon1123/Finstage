package io.dustin.finstage.user.application.service;

import io.dustin.finstage.common.exception.custom.InvalidDepartmentException;
import io.dustin.finstage.user.application.dto.MsGraphUserInfo;
import io.dustin.finstage.user.application.dto.MsGraphUserInfo.Member;
import io.dustin.finstage.user.application.port.in.query.GetUsersInfoQuery;
import io.dustin.finstage.user.application.port.out.LoadUsersInfoPort;
import io.dustin.finstage.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetUsersInfoServiceTest {

    LoadUsersInfoPort loadUsersInfoPort = mock(LoadUsersInfoPort.class);
    GetUsersInfoService service = new GetUsersInfoService(loadUsersInfoPort);

    @Test
    @DisplayName("파싱된 부서 리스트가 비어 있으면 예외 발생")
    void shouldThrowExceptionWhenParsedDepartmentListIsEmpty() {
        GetUsersInfoQuery query = new GetUsersInfoQuery(""); // 빈 select 값 → empty list

        assertThatThrownBy(() -> service.getUsersInfo(query))
                .isInstanceOf(InvalidDepartmentException.class)
                .hasMessage("올바른 부서 정보를 입력해야 합니다.");
    }

    @Test
    @DisplayName("MS Graph 응답이 null이면 예외 발생")
    void shouldThrowExceptionWhenMsGraphReturnsNull() {
        GetUsersInfoQuery query = new GetUsersInfoQuery("부서1");

        when(loadUsersInfoPort.getUsersInfoFromMsGraph(any())).thenReturn(null);

        assertThatThrownBy(() -> service.getUsersInfo(query))
                .isInstanceOf(InvalidDepartmentException.class)
                .hasMessage("올바른 부서 정보를 입력해야 합니다.");
    }

    @Test
    @DisplayName("MS Graph 응답이 빈 리스트이면 예외 발생")
    void shouldThrowExceptionWhenMsGraphReturnsEmpty() {
        GetUsersInfoQuery query = new GetUsersInfoQuery("부서1");

        when(loadUsersInfoPort.getUsersInfoFromMsGraph(any())).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> service.getUsersInfo(query))
                .isInstanceOf(InvalidDepartmentException.class)
                .hasMessage("올바른 부서 정보를 입력해야 합니다.");
    }

    @Test
    @DisplayName("정상적으로 사용자 목록을 반환한다.")
    void shouldReturnUserList() {
        Member member1 = new Member("홍길동", "hong@example.com");
        MsGraphUserInfo dept = new MsGraphUserInfo("부서1", List.of(member1));
        when(loadUsersInfoPort.getUsersInfoFromMsGraph(any()))
                .thenReturn(List.of(dept));

        GetUsersInfoQuery query = new GetUsersInfoQuery("부서1");

        List<User> result = service.getUsersInfo(query);

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getDisplayName()).isEqualTo("홍길동");
        assertThat(result.getFirst().getEmail()).isEqualTo("hong@example.com");
        assertThat(result.getFirst().getDepartmentName()).isEqualTo("부서1");
    }
}
