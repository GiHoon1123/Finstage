package io.dustin.salesmgmt.user.adapter.out.msgraph;

import io.dustin.salesmgmt.user.adpater.out.msgraph.LoadUsersInfoFromMsGraphAdapter;
import io.dustin.salesmgmt.user.adpater.out.msgraph.MockMsGraphUserClient;
import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
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
    void shouldReturnAllDepartment_whenInputIsNull(){
        // when
        List<MsGraphUserInfo> result = adapter.getUsersInfoFromMsGraph(null);

        // then
        assertThat(result).hasSize(5);
    }

    @Test
    void shouldReturnAllDepartment_whenInputIsEmpty(){
        // when
        List<MsGraphUserInfo> result = adapter.getUsersInfoFromMsGraph(List.of());

        // then
        assertThat(result).hasSize(5);
    }

    @Test
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
    void shouldReturnEmpty_whenNoMatchingDepartments(){
        // given
        List<String> department = List.of("NoExistent Team");

        // when
        List<MsGraphUserInfo> result = adapter.getUsersInfoFromMsGraph(department);

        // then
        assertThat(result).isEmpty();
    }





}
