package io.dustin.salesmgmt.common.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CommonResponseTest {

    @Test
    @DisplayName("success 메서드는 상태코드 200, 메시지 'Success', 데이터를 포함하고 errors는 null인  응답을 생성한다.")
    void shouldReturnSuccessResponse() {
        String data = "Test Data";
        CommonResponse<String> response = CommonResponse.success(data);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getData()).isEqualTo("Test Data");
        assertThat(response.getErrors()).isNull();
    }

    @Test
    @DisplayName("of 메서드는 전달받은 값으로 응답을 생성하고, errors는 null인 응답을 생성한다.")
    void shouldCreateCustomResponse() {
        String data = "hello";
        CommonResponse<String> response = CommonResponse.of(404, "Not Found", data);

        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getMessage()).isEqualTo("Not Found");
        assertThat(response.getData()).isEqualTo("hello");
        assertThat(response.getErrors()).isNull();
    }


    @Test
    @DisplayName("of 메서드는 status, message, data, errors를 모두 포함한 응답을 생성한다.")
    void shouldCreateCustomResponseWithErrors() {
        List<CommonResponse.ValidationError> errors  = List.of(
                new CommonResponse.ValidationError("field1", "must not be null"),
                new CommonResponse.ValidationError("field2", "must be a valid email")
        );

        CommonResponse<String> response = CommonResponse.of(400, "Validation Error", null, errors);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.getMessage()).isEqualTo("Validation Error");
        assertThat(response.getData()).isNull();
        assertThat(response.getErrors())
                .hasSize(2)
                .extracting(CommonResponse.ValidationError::getField)
                .containsExactly("field1","field2");
    }


}
