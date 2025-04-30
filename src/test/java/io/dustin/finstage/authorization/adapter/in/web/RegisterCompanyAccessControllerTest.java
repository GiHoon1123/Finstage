package io.dustin.finstage.authorization.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dustin.finstage.authorization.application.port.in.RegisterCompanyAccessUseCase;
import io.dustin.finstage.authorization.application.port.in.command.RegisterCompanyAccessCommand;
import io.dustin.finstage.common.util.SlackNotifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegisterCompanyAccessController.class)
public class RegisterCompanyAccessControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RegisterCompanyAccessUseCase registerCompanyAccessUseCase;

    @MockBean
    SlackNotifier slackNotifier; // 전역 예외처리기에서 필요함

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("정상 요청 시 200 ok와 true를 반환한다.")
    void shouldRegisterSuccessfully() throws Exception {
        RegisterCompanyAccessCommand command = new RegisterCompanyAccessCommand(
                "dustin",
                "dustin@naver.com",
                List.of(new RegisterCompanyAccessCommand.CompanyInfo(1L, "회사A"))
        );

        when(registerCompanyAccessUseCase.register(any())).thenReturn(true);

        mockMvc.perform(post("/authorization/company-access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    @DisplayName("필수 값 누락 시 400 BadRequest를 반환한다")
    void shouldReturnBadRequestWhenMissingField() throws Exception {
        RegisterCompanyAccessCommand command = new RegisterCompanyAccessCommand(
                "", // userName 누락
                "dustin@naver.com",
                List.of(new RegisterCompanyAccessCommand.CompanyInfo(1L, "회사A"))
        );

        mockMvc.perform(post("/authorization/company-access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0].field").value("userName"));
    }

    @Test
    @DisplayName("타입 불일치 시 400 BadRequest를 반환한다")
    void shouldReturnBadRequestWhenInvalidType() throws Exception {
        // companyId를 문자열로 보냄
        String invalidJson = """
            {
              "userName": "dustin",
              "userEmail": "dustin@naver.com",
              "companies": [
                {
                  "companyId": "문자열",
                  "companyName": "회사A"
                }
              ]
            }
        """;

        mockMvc.perform(post("/authorization/company-access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors[0].field").value("companies[0].companyId"));
    }

    @Test
    @DisplayName("서버 내부 예외 발생 시 500 Internal Server Error를 반환한다")
    void shouldReturnInternalServerErrorOnUnexpectedException() throws Exception {
        when(registerCompanyAccessUseCase.register(any()))
                .thenThrow(new RuntimeException("DB down"));

        RegisterCompanyAccessCommand command = new RegisterCompanyAccessCommand(
                "dustin",
                "dustin@naver.com",
                List.of(new RegisterCompanyAccessCommand.CompanyInfo(1L, "회사A"))
        );

        mockMvc.perform(post("/authorization/company-access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500));
    }
}
