package io.dustin.salesmgmt.user.application.port.in.query;

import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetUsersInfoQuery {

    @NotNull(message = "부서명은 필수입니다.")
    private final String select;

    public GetUsersInfoQuery(
            String select
    ) {
        this.select = select;
    }

    public List<String> getDepartmentList(){
        if (select == null || select.isBlank()) return Collections.emptyList();
        return Arrays.stream(select.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

}
