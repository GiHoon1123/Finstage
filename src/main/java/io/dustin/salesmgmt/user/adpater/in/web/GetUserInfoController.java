package io.dustin.salesmgmt.user.adpater.in.web;

import io.dustin.salesmgmt.user.application.dto.UserResponse;
import io.dustin.salesmgmt.user.application.port.in.GetUserInfoUseCase;
import io.dustin.salesmgmt.user.application.port.in.query.GetUserInfoQuery;
import io.dustin.salesmgmt.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class GetUserInfoController {

    private final GetUserInfoUseCase getUserInfoUseCase;

    /**
     * 사용자 정보 조회 API
     *
     * 전체 조회:         GET /users
     * 부서 선택 조회:    GET /users?select=sotGroup,radGroup
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(required = false) String select
            )
    {
        GetUserInfoQuery query = new GetUserInfoQuery(select);
        List<User> users = getUserInfoUseCase.getUsers(query);
        List<UserResponse>  response = users.stream()
                .map(UserResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }




}
