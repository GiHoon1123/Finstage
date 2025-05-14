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
public class RegisterCompanyAccessController {

    private final RegisterCompanyAccessUseCase registerCompanyAccessUseCase;

    @Operation(
            summary = "Register company access permissions for a user",
            description = """
        Registers company access permissions for a user.

        This API receives a user's email, name, and a list of company IDs the user is allowed to access.

        - The request body must be in JSON format.
        - You can register access to multiple companies at once.
        - If a company access entry already exists for the user, it will be ignored (no duplication error).

        Example request:

        {
          "email": "user@example.com",
          "name": "John Doe",
          "companyIds": [101, 102, 103]
        }
        """
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
