package io.dustin.salesmgmt.user.adapter.out.msgraph;

import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LoadUsersInfoFromMsGraphAdapterTest {

    private LoadUsersInfoFromMsGraphAdapter adapter;

    @BeforeEach
    void setUp() {
        MockMsGraphUserClient client = new MockMsGraphUserClient();
        adapter = new LoadUsersInfoFromMsGraphAdapter(client);
    }

    @Test
    @DisplayName("null을 입력했을 때 모든 값이 오는지 확인한다.")
    void shouldReturnAllDepartment_whenInputIsNull(){
        // when
        List<MsGraphUserInfo> result = adapter.getUsersInfoFromMsGraph(null);

        // then
        assertThat(result).hasSize(5);
    }

    @Test
    @DisplayName("값을 입력하지 않았을 때 모든 값이 오는지 확인한다.")
    void shouldReturnAllDepartment_whenInputIsEmpty(){
        // when
        List<MsGraphUserInfo> result = adapter.getUsersInfoFromMsGraph(List.of());

        // then
        assertThat(result).hasSize(5);
    }

    @Test
    @DisplayName("특정 부서를 입력했을 때 해당하는 값이 오는지 확인한다.")
    void shouldReturnFilteredDepartments_whenValidNameProvided(){
        // given
        List<String> departments = List.of("Development Team", "Finance Team");

        // when
        List<MsGraphUserInfo> result = adapter.getUsersInfoFromMsGraph(departments);

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(MsGraphUserInfo::name)
                .containsExactlyInAnyOrder("Development Team", "Finance Team");
    }

    @Test
    @DisplayName("유효하지 않은 부서를 입력했을 때 빈 값이 오는지 확인한다.")
    void shouldReturnEmpty_whenNoMatchingDepartments(){
        // given
        List<String> department = List.of("NoExistent Team");

        // when
        List<MsGraphUserInfo> result = adapter.getUsersInfoFromMsGraph(department);

        // then
        assertThat(result).isEmpty();
    }





}
