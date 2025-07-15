package io.dustin.finstage.financial.dto

import io.dustin.finstage.financial.domain.CashFlowStatement
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class CashFlowStatementResponse(
        @Schema(description = "기업 심볼", example = "AAPL")
        val symbol: String,

        @Schema(description = "보고일자", example = "2024-09-28")
        val date: LocalDate,

        @Schema(description = "영업활동 현금흐름", example = "118254000000")
        val operatingCashFlow: Long,

        @Schema(description = "투자활동 현금흐름", example = "2935000000")
        val investingCashFlow: Long,

        @Schema(description = "재무활동 현금흐름", example = "-121983000000")
        val financingCashFlow: Long,

        @Schema(description = "잉여현금흐름", example = "108807000000")
        val freeCashFlow: Long
) {
    companion object {
        fun from(cashFlow: CashFlowStatement): CashFlowStatementResponse {
            return CashFlowStatementResponse(
                    symbol = cashFlow.symbol,
                    date = cashFlow.date,
                    operatingCashFlow = cashFlow.operatingCashFlow,
                    investingCashFlow = cashFlow.investingCashFlow,
                    financingCashFlow = cashFlow.financingCashFlow,
                    freeCashFlow = cashFlow.freeCashFlow
            )
        }
    }
}
