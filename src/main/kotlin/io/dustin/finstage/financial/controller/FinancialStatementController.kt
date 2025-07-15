package io.dustin.finstage.financial.controller

import io.dustin.finstage.common.response.CommonResponse
import io.dustin.finstage.financial.dto.FinancialStatementRequest
import io.dustin.finstage.financial.dto.FinancialStatementResponse
import io.dustin.finstage.financial.service.FinancialStatementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "FinancialStatement")
@RestController
@RequestMapping("/financials")
class FinancialStatementController(
        private val financialStatementService: FinancialStatementService
) {

    @GetMapping
    @Operation(
            summary = "재무제표 조회",
            description = "특정 심볼에 대한 최근 5개년 재무제표 데이터를 조회합니다. (income_statement, balance_sheet, cash_flow 포함)"
    )
    fun getFinancialStatements(
            @Parameter(description = "심볼 티커", example = "AAPL")
            @RequestParam symbol: String
    ): CommonResponse<FinancialStatementResponse?> {
        val request = FinancialStatementRequest.of(symbol)
        val response = financialStatementService.getFinancialStatements(request)
        return CommonResponse.success(response)
    }
}
