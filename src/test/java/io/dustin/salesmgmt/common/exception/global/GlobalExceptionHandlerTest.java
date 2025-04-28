package io.dustin.salesmgmt.common.exception.global;

import io.dustin.salesmgmt.common.exception.custom.InvalidDepartmentException;
import io.dustin.salesmgmt.common.util.SlackNotifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * GlobalExceptionHandler의 동작을 검증하는 테스트
 */
@WebMvcTest(controllers = GlobalExceptionHandlerTest.DummyController.class)
@Import({GlobalExceptionHandler.class})
@ContextConfiguration(classes = {GlobalExceptionHandlerTest.DummyController.class, GlobalExceptionHandlerTest.DummySlackNotifier.class})
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 예외를 발생시키는 더미 컨트롤러
     */
    @RestController
    public static class DummyController {
        @GetMapping("/trigger-exception")
        public void trigger() {
            throw new InvalidDepartmentException("테스트 부서 예외 발생");
        }
    }

    /**
     * SlackNotifier를 대체할 더미 빈
     * - 실제 슬랙 전송을 막기 위함
     */
    static class DummySlackNotifier extends SlackNotifier {
        public DummySlackNotifier() {
            super(new RestTemplate()); // 그냥 아무거나 넘겨
        }

        @Override
        public void send(String message) {
            // 아무것도 하지 않음
        }
    }

    @Test
    @DisplayName("InvalidDepartmentException이 발생하면 400 응답과 메시지를 반환한다.")
    void shouldReturnInvalidDepartmentException() throws Exception {
        mockMvc.perform(get("/trigger-exception"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("테스트 부서 예외 발생"))
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors").doesNotExist()); // errors도 없는지 체크!
    }
}
