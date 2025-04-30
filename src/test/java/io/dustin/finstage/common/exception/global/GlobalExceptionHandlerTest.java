package io.dustin.finstage.common.exception.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dustin.finstage.common.exception.custom.InvalidDepartmentException;
import io.dustin.finstage.common.util.SlackNotifier;
import jakarta.validation.constraints.Min;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * GlobalExceptionHandler의 동작을 검증하는 테스트
 */
@WebMvcTest(controllers = GlobalExceptionHandlerTest.DummyController.class)
@Import({GlobalExceptionHandler.class})
@ContextConfiguration(classes = {
        GlobalExceptionHandlerTest.DummyController.class,
        GlobalExceptionHandlerTest.DummySlackNotifier.class
})
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 예외를 발생시키는 더미 컨트롤러
     */
    @RestController
    @Validated
    public static class DummyController {

        @GetMapping("/trigger-invalid-dept")
        public void invalidDept() {
            throw new InvalidDepartmentException("테스트 부서 예외 발생");
        }

        @GetMapping("/trigger-constraint")
        public void constraintViolation(@RequestParam @Min(value = 10, message = "10 이상 입력해야 합니다.") int age) {
            // 컨트롤러 자체는 비어 있어도 됨
        }

        @GetMapping("/trigger-exception")
        public void unexpected() {
            throw new RuntimeException("예상치 못한 에러!");
        }

        @PostMapping("/trigger-type-mismatch")
        public void typeMismatch(@RequestBody SampleDto dto) {
            // Jackson에서 타입 변환 실패 유도용
        }

        public record SampleDto(String name, Long number) {}
    }

    /**
     * SlackNotifier를 대체할 더미 빈
     */
    static class DummySlackNotifier extends SlackNotifier {
        public DummySlackNotifier() {
            super(new RestTemplate());
        }

        @Override
        public void send(String message) {
            // 슬랙 전송 막기
        }
    }

    @Test
    @DisplayName("InvalidDepartmentException이 발생하면 400 응답과 메시지를 반환한다.")
    void shouldReturnInvalidDepartmentException() throws Exception {
        mockMvc.perform(get("/trigger-invalid-dept"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("테스트 부서 예외 발생"))
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors").doesNotExist());
    }

    @Test
    @DisplayName("ConstraintViolationException이 발생하면 400과 오류 필드 정보를 반환한다.")
    void shouldReturnConstraintViolationException() throws Exception {
        mockMvc.perform(get("/trigger-constraint?age=5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors[0].field").value("age"))
                .andExpect(jsonPath("$.errors[0].message").value("10 이상 입력해야 합니다."));
    }

    @Test
    @DisplayName("InvalidFormatException이 발생하면 400과 타입 오류 메시지를 반환한다.")
    void shouldReturnInvalidFormatException() throws Exception {
        String invalidJson = """
            {
              "name": "dustin",
              "number": "문자열"
            }
        """;

        mockMvc.perform(post("/trigger-type-mismatch")
                        .contentType("application/json")
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors[0].field").value("number"))
                .andExpect(jsonPath("$.errors[0].message").value("숫자만 입력 가능합니다."));
    }

    @Test
    @DisplayName("예상치 못한 에러 발생 시 500과 메시지를 반환한다.")
    void shouldReturnInternalServerError() throws Exception {
        mockMvc.perform(get("/trigger-exception"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("서버 내부 오류가 발생했습니다."))
                .andExpect(jsonPath("$.errors").doesNotExist());
    }
}
