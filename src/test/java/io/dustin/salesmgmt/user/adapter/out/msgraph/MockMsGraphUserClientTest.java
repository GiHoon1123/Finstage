package io.dustin.salesmgmt.user.adapter.out.msgraph;


import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

// 현재는 내부 mock JSON이므로 new로 직접 생성해도 OK
// 추후 실제 API 호출시에는 외부 의존성을 고려해 DI 또는 Mock으로 대체
// 외부 시스템 연결되면 테스트 안정성을 위해 Mockito 활용 권장
@Component
public class MockMsGraphUserClientTest {
    private final MockMsGraphUserClient client = new  MockMsGraphUserClient();

    @Test
    @DisplayName("모킹된 모든 데이터가 정상적으로 리턴되는지 확인한다.")
    void shouldReturnAllDepartmentFromMockJson(){
        // when
        List<MsGraphUserInfo> department = client.getAllDepartments();

        // then
        assertThat(department).hasSize(5);
        assertThat(department.getFirst().name()).isEqualTo("Development Team");
        assertThat(department.getFirst().members()).isNotEmpty();
    }

    @Test
    @DisplayName("특정 부서를 입력했을 때 해당하는 값이 오는지 확인한다.")
    void ShouldReturnOnlySelectedDepartments(){
        // given
        List<String> filter = List.of("Development Team", "Finance Team");

        // when
        List<MsGraphUserInfo> result = client.getDepartmentsByName(filter);

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(MsGraphUserInfo::name)
                .containsExactlyInAnyOrder("Development Team", "Finance Team");

    }


    @Test
    @DisplayName("유효하지 않은 부서를 입력했을 때 빈 값이 오는지 확인한다.")
    void shouldReturnEmptyListWhenDepartmentDoesNotExist(){
        // given
        List<String> filter = List.of("Unknown Department");

        // when
        List<MsGraphUserInfo> result = client.getDepartmentsByName(filter);

        // then
        assertThat(result).isEmpty();
    }



}
