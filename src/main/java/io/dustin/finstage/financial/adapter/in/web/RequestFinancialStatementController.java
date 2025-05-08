package io.dustin.finstage.financial.adapter.in.web;

import io.dustin.finstage.common.annotation.WebAdapter;
import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.financial.application.port.in.RequestFinancialStatementUseCase;
import io.dustin.finstage.financial.application.port.in.command.RequestFinancialStatementCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/financials")
public class RequestFinancialStatementController {

    private final RequestFinancialStatementUseCase requestUseCase;

    @Operation(summary = "재무제표 등록 요청", description = "심볼을 입력받아 재무제표 수집을 요청합니다.")
    @PostMapping("/request")
    public ResponseEntity<CommonResponse<String>> requestFinancial(
            @Parameter(
                    name = "symbol",
                    description = "요청할 종목 심볼",
                    required = true,
                    schema = @Schema(example = "GOOGL")
            )
            @RequestParam String symbol
    ) {
        RequestFinancialStatementCommand command = new RequestFinancialStatementCommand(symbol);
        requestUseCase.request(command);
        return ResponseEntity.ok(CommonResponse.success("요청이 성공적으로 접수되었습니다."));
    }
}

