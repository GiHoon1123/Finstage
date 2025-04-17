package io.dustin.salesmgmt.user.application.service;

import io.dustin.salesmgmt.common.exception.custom.InvalidDepartmentException;
import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;
import io.dustin.salesmgmt.user.application.port.in.query.GetUsersInfoQuery;
import io.dustin.salesmgmt.user.application.port.out.LoadUsersInfoPort;
import io.dustin.salesmgmt.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class GetUsersInfoServiceTest {

    private LoadUsersInfoPort loadUsersInfoPort;
    private GetUsersInfoService getUsersInfoService;

    @BeforeEach
    void setUp() {
        loadUsersInfoPort = mock(LoadUsersInfoPort.class);
        getUsersInfoService = new GetUsersInfoService(loadUsersInfoPort);
    }

    @Test
    void shouldReturnUsers_WhenValidDepartmentIsGiven(){
        // given
        GetUsersInfoQuery query = new GetUsersInfoQuery("devGroup");
        MsGraphUserInfo.Member member = new MsGraphUserInfo.Member("dustin", "dustin@naver.com");
        MsGraphUserInfo msGraphUserInfo = new MsGraphUserInfo("devGroup", List.of(member));

        // 모킹된 객체가 어떤 값을 리턴할지 정의한다.
        when(loadUsersInfoPort.getUsersInfoFromMsGraph(query.getDepartmentList()))
                .thenReturn(List.of(msGraphUserInfo));

        // when
        List<User> result = getUsersInfoService.getUsersInfo(query);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDisplayName()).isEqualTo("dustin");
        assertThat(result.getFirst().getDepartmentName()).isEqualTo("devGroup");
        assertThat(result.getFirst().getEmail()).isEqualTo("dustin@naver.com");
    }

    @Test
    void shouldThrowException_whenInvalidDepartmentFound(){
        // given
        GetUsersInfoQuery query = new GetUsersInfoQuery("InvalidGroup");
        when(loadUsersInfoPort.getUsersInfoFromMsGraph(query.getDepartmentList()))
                .thenReturn(List.of());

        // when & then
        assertThatThrownBy(()-> getUsersInfoService.getUsersInfo(query))
                .isInstanceOf(InvalidDepartmentException.class)
                .hasMessage("올바른 부서 정보를 입력해야 합니다.");

    }
}
