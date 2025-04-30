package io.dustin.finstage.common.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class PaginatedResponseTest {


    // 전체 데이터 25개가 있고, 그 중 2페이지의 10개 중 필터링을 통해 3개만 데이터가 채워졌다.
    @Test
    @DisplayName("success 메서드는 기본 상태값과 페이지 정보를 기반으로 응답을 생성한다.")
    void shouldCreateSuccessPaginatedResponse() {
        List<String> data = List.of("data1", "data2", "data3");
        PaginatedResponse<String> response = PaginatedResponse.success(25,2,10,data);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getTotal()).isEqualTo(25);
        assertThat(response.getPage()).isEqualTo(2);
        assertThat(response.getLimit()).isEqualTo(10);
        assertThat(response.getTotalPages()).isEqualTo(3);
        assertThat(response.getRemainingPages()).isEqualTo(1);
        assertThat(response.getData()).isEqualTo(data);
    }

    @Test
    @DisplayName("of 메서드는 사용자 정의 응답을 생성한다.")
    void shouldCreateCustomPaginateResponse(){
        List<String> data = List.of("a","b");
        PaginatedResponse<String> response = PaginatedResponse.of(201,"Created",5,1,2,data);

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getMessage()).isEqualTo("Created");
        assertThat(response.getTotalPages()).isEqualTo(3);
        assertThat(response.getRemainingPages()).isEqualTo(2);
        assertThat(response.getData()).isEqualTo(data);


    }

}
