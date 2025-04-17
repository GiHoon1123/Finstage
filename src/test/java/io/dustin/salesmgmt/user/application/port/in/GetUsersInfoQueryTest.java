package io.dustin.salesmgmt.user.application.port.in;

import io.dustin.salesmgmt.user.application.port.in.query.GetUsersInfoQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class GetUsersInfoQueryTest {

    @Test
    @DisplayName("파라미터 값이 없을 때 빈 리스트를 반환하는지 확인한다.")
    void shouldReturnEmptyListWhenSelectIsNull(){
        // given
        GetUsersInfoQuery query = new GetUsersInfoQuery(null);

        // when
        List<String> result = query.getDepartmentList();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("파라미터로 ' ' 을 보냈을 때  빈 리스트가 오는지 확인한다.")
    void shouldReturnEmptyListWhenSelectIsBlank(){
        // given
        GetUsersInfoQuery query = new GetUsersInfoQuery(" ");

        // when
        List<String> result = query.getDepartmentList();

        // then
        assertThat(result).isEmpty();

    }

    @Test
    @DisplayName("연속된 부서를 입력받았을 때 ,를 기준으로 분리되는지 확인한다.")
    void shouldSplitCommaSeparatedDepartment(){
        // given
        String input = "sotGroup, radGroup, devGroup";
        GetUsersInfoQuery query = new GetUsersInfoQuery(input);

        // when
        List<String> result = query.getDepartmentList();


        // then
        assertThat(result).containsExactly("sotGroup", "radGroup", "devGroup");

    }

    @Test
    @DisplayName("중간에 빈 문자열이 있을때 무시하는지 확인한다.")
    void shouldIgnoreEmptyValuesInSelect(){
        // given
        GetUsersInfoQuery query = new GetUsersInfoQuery("sotGroup,, ,radGroup");

        List<String> result = query.getDepartmentList();

        // then
        assertThat(result).containsExactly("sotGroup", "radGroup");

    }

}
