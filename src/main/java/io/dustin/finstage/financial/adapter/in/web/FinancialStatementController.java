package io.dustin.finstage.financial.adapter.in.web;


import io.dustin.finstage.common.annotation.WebAdapter;
import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.financial.application.port.in.GetFinancialStatementUseCase;
import io.dustin.finstage.financial.domain.FinancialStatement;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/financials")
public class FinancialStatementController {

    private final GetFinancialStatementUseCase getFinancialStatementUseCase;

    /**
     * 재무제표 조회 API
     *
     * 클라이언트는 심볼을 전달하여 해당 종목의 재무제표를 조회할 수 있다.
     * 예: /financials?symbol=GOOGL
     */
    @GetMapping
    public ResponseEntity<CommonResponse<FinancialStatement>> getFinancialStatements(
            @Parameter(
                    name = "symbol",
                    description = "조회할 종목 심볼입니다. 예: GOOGL",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "string", example = "GOOGL")
            )
            @RequestParam String symbol
    ) {
        FinancialStatement result = getFinancialStatementUseCase.getBySymbol(symbol);
        return ResponseEntity.ok(CommonResponse.success(result));
    }
}
