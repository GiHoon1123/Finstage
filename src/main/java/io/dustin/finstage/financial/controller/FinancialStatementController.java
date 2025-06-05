package io.dustin.finstage.financial.controller;

import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.financial.dto.FinancialStatementRequest;
import io.dustin.finstage.financial.dto.FinancialStatementResponse;
import io.dustin.finstage.financial.service.FinancialStatementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "FinancialStatement")
@RestController
@RequiredArgsConstructor
@RequestMapping("/financials")
public class FinancialStatementController {

    private final FinancialStatementService financialStatementService;

    @GetMapping
    @Operation(
            summary = "재무제표 조회",
            description = "특정 심볼에 대한 최근 5개년 재무제표 데이터를 조회합니다. (income_statement, balance_sheet, cash_flow 포함)"
    )
    public CommonResponse<FinancialStatementResponse> getFinancialStatements(
            @Parameter(description = "심볼 티커", example = "AAPL")
            @RequestParam String symbol
    ) {
        FinancialStatementRequest request = FinancialStatementRequest.of(symbol);
        FinancialStatementResponse response = financialStatementService.getFinancialStatements(request);
        return CommonResponse.success(response);
    }
}
