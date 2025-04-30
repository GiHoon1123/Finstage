package io.dustin.salesmgmt.authorization.application.port.in.command;

import io.dustin.salesmgmt.authorization.adapter.out.persistence.LoadUserCompanyAccessPort;
import io.dustin.salesmgmt.authorization.application.port.out.RegisterUserCompanyAccessPort;
import io.dustin.salesmgmt.authorization.application.service.RegisterCompanyAccessService;
import io.dustin.salesmgmt.authorization.domain.Company;
import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;
import io.dustin.salesmgmt.common.exception.custom.AlreadyRegisteredCompanyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterCompanyAccessCommandTest {

    private RegisterCompanyAccessService registerCompanyAccessService;
    private RegisterUserCompanyAccessPort registerUserCompanyAccessPort;
    private LoadUserCompanyAccessPort loadUserCompanyAccessPort;

    @BeforeEach
    void setUp() {
         registerUserCompanyAccessPort = mock(RegisterUserCompanyAccessPort.class);
         loadUserCompanyAccessPort = mock(LoadUserCompanyAccessPort.class);

         registerCompanyAccessService = new RegisterCompanyAccessService(registerUserCompanyAccessPort, loadUserCompanyAccessPort);
    }

    @Test
    @DisplayName("중복된 회사가 없으면 정상 적으로 작동하는지 확인한다.")
    void shouldRegisterSuccessfullyWhenNoDuplicate() {
        // given
        RegisterCompanyAccessCommand command = new RegisterCompanyAccessCommand(
                "dustin",
                "dustin@naver.com",
                List.of(new RegisterCompanyAccessCommand.CompanyInfo(1L,"Test company"))
        );

        // 중복된 회사 없음
        when(loadUserCompanyAccessPort.findExistingCompanyAccesses(anyString(), anyString(), anyList()))
                .thenReturn(UserCompanyAccess.of("dustin","dustin@naver.com", List.of()));

        // when
        boolean result = registerCompanyAccessService.register(command);

        // then
        assertThat(result).isTrue();
        verify(registerUserCompanyAccessPort, times(1)).save(any());
    }

    @Test
    @DisplayName("이미 등록된 회사가 있으면 예외를 던진다.")
    void shouldThrowExceptionWhenDuplicateCompanyExists() {
        // given
        RegisterCompanyAccessCommand command = new RegisterCompanyAccessCommand(
                "dustin",
                "dustin@naver.com",
                List.of(new RegisterCompanyAccessCommand.CompanyInfo(1L, "Test company"))
        );

        Company duplicateCompany = Company.of(1L, "Test company");

        when(loadUserCompanyAccessPort.findExistingCompanyAccesses(anyString(),anyString(),anyList()))
                .thenReturn(UserCompanyAccess.of("dustin","dustin@naver.com",List.of(duplicateCompany))
        );

        // when & then
        assertThatThrownBy(()-> registerCompanyAccessService.register(command))
                .isInstanceOf(AlreadyRegisteredCompanyException.class)
                .hasMessageContaining("이미 등록된 회사가 존재합니다");


        verify(registerUserCompanyAccessPort, never()).save(any());
    }
}
