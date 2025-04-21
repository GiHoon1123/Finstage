package io.dustin.salesmgmt.common.exception.global;


import io.dustin.salesmgmt.common.exception.custom.InvalidDepartmentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GlobalExceptionHandlerTest.DummyController.class)
@Import(GlobalExceptionHandler.class)
@ComponentScan(basePackageClasses = GlobalExceptionHandlerTest.DummyController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @RestController
    public static class DummyController {
        @GetMapping("/trigger-exception")
        public void trigger(){
            throw new InvalidDepartmentException("테스트 부서 예외 발생");
        }
    }

    @Test
    @DisplayName("InvalidDepartmentException이 발생하면 400 응답과 메시지를 반환한다")
    void shouldReturnInvalidDepartmentException() throws Exception {
        mockMvc.perform(get("/trigger-exception"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("테스트 부서 예외 발생"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }


}
