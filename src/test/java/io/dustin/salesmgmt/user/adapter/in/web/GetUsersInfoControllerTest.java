package io.dustin.salesmgmt.user.adapter.in.web;

import io.dustin.salesmgmt.common.util.SlackNotifier;
import io.dustin.salesmgmt.user.application.port.in.GetUsersInfoUseCase;
import io.dustin.salesmgmt.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GetUsersInfoController.class)
@DisplayName("클라이언트의 요청을 받아 유스케이스가 정상적으로 처리하는지 확인한다.")
public class GetUsersInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * @WebMvcTest는 컨트롤러와 관련된 빈만 로드하기 때문에, (Controller (@Controller, @RestController), ControllerAdvice, WebMvcConfigurer, MockMvc 등)
     * GlobalExceptionHandler가 사용하는 SlackNotifier(@Component)는 자동으로 로드되지 않는다.
     * 따라서 테스트에서는 SlackNotifier를 직접 @MockBean으로 주입해줘야 함.
     * (안 그러면 GlobalExceptionHandler 생성 시점에 의존성 오류 발생)
     * 추후 개선 예정 (04/28)
     */
    @MockBean
    private SlackNotifier slackNotifier;

    @MockBean
    private GetUsersInfoUseCase getUsersInfoUseCase;


    @Test
    void shouldReturnUsersWhenValidQueryProvided() throws  Exception {
        List<User> users = List.of(User.of("Alice", "alice@naver.com", "Development Team"));
        when(getUsersInfoUseCase.getUsersInfo(any())).thenReturn(users);

        mockMvc.perform(get("/users?select=Development Team"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].displayName").value("Alice"))
                .andExpect(jsonPath("$.data[0].email").value("alice@naver.com"))
                .andExpect(jsonPath("$.data[0].departmentName").value("Development Team"));
    }
}
