package io.dustin.finstage.user.adapter.in.web;

import io.dustin.finstage.common.annotation.WebAdapter;
import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.user.application.dto.UserResponse;
import io.dustin.finstage.user.application.port.in.GetUsersInfoUseCase;
import io.dustin.finstage.user.application.port.in.query.GetUsersInfoQuery;
import io.dustin.finstage.user.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class GetUsersInfoController {

    private final GetUsersInfoUseCase getUserInfoUseCase;

    /**
     * 사용자 정보 조회 API
     *
     * 전체 조회:  GET /users
     * 부서 선택 조회: GET /users?select=Development Team,Corporate Sales Team
     */
    @GetMapping
    public ResponseEntity<CommonResponse<List<UserResponse>>> getUsers(
            @Parameter(
                    name = "select",
                    description = "조회할 부서 이름 (여러 개는 쉼표로 구분).<br>" +
                            "예시: Development Team, Corporate Sales Team, Support Team<br>" +
                            "전체 부서를 가져오고 싶은 경우 아무것도 입력하지 않는다.",
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "string", example = "Development Team,Support Team")
            )
            @RequestParam(required = false) String select
    ) {
        GetUsersInfoQuery query = new GetUsersInfoQuery(select);
        List<User> users = getUserInfoUseCase.getUsersInfo(query);
        List<UserResponse> response = users.stream()
                .map(UserResponse::from)
                .toList();
        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
