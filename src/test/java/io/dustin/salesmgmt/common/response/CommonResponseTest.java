package io.dustin.salesmgmt.common.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class CommonResponseTest {

    @Test
    @DisplayName("success 메서드는 상태코드 200, 메시지 'Success', 데이터를 포함한 응답을 생성한다.")
    void shouldReturnSuccessResponse() {
        String data = "Test Data";
        CommonResponse<String> response = CommonResponse.success(data);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getData()).isEqualTo("Test Data");
    }

    @Test
    @DisplayName("of 메서드는 전달받은 값으로 응답을 생성한다.")
    void shouldCreateCustomResponse() {
        String data = "hello";
        CommonResponse<String> response = CommonResponse.of(404, "Not Found", data);

        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getMessage()).isEqualTo("Not Found");
        assertThat(response.getData()).isEqualTo("hello");



    }


}
