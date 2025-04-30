package io.dustin.finstage.authorization.adapter.in.web;

import io.dustin.finstage.authorization.application.port.in.RegisterCompanyAccessUseCase;
import io.dustin.finstage.authorization.application.port.in.command.RegisterCompanyAccessCommand;
import io.dustin.finstage.common.annotation.WebAdapter;
import io.dustin.finstage.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/authorization/company-access")
@Tag(name = "회사 접근 권한", description = "유저의 회사 접근 권한을 등록하는 API")
public class RegisterCompanyAccessController {

    private final RegisterCompanyAccessUseCase registerCompanyAccessUseCase;

    @Operation(
            summary = "회사 접근 권한 등록",
            description = "유저의 이메일, 이름과 접근할 회사 목록을 받아 등록합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 매개변수로 인해 요청이 실패했습니다.")
    })
    @PostMapping
    public ResponseEntity<CommonResponse<Boolean>> registerCompanyAccess(
            @RequestBody @Valid RegisterCompanyAccessCommand command
    ) {
        Boolean response = registerCompanyAccessUseCase.register(command);
        return ResponseEntity.ok(CommonResponse.success(response));
    }




}
