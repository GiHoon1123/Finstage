package io.dustin.finstage.financial.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.dustin.finstage.financial.domain.IncomeStatement
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class IncomeStatementResponse(
        @Schema(description = "기업 심볼", example = "AAPL")
        val symbol: String,

        @Schema(description = "보고일자", example = "2024-09-28")
        val date: LocalDate,

        @Schema(description = "매출", example = "391035000000")
        val revenue: Long,

        @Schema(description = "매출원가", example = "210352000000")
        val costOfRevenue: Long,

        @Schema(description = "매출총이익", example = "180683000000")
        val grossProfit: Long,

        @Schema(description = "영업이익", example = "123216000000")
        val operatingIncome: Long,

        @Schema(description = "주당순이익(EPS)", example = "6.11")
        val eps: Double,

        @Schema(description = "순이익", example = "93736000000")
        val netIncome: Long
) {
    companion object {
        fun from(incomeStatement: IncomeStatement): IncomeStatementResponse {
            return IncomeStatementResponse(
                    symbol = incomeStatement.symbol,
                    date = incomeStatement.date,
                    revenue = incomeStatement.revenue,
                    costOfRevenue = incomeStatement.costOfRevenue,
                    grossProfit = incomeStatement.grossProfit,
                    operatingIncome = incomeStatement.operatingIncome,
                    eps = incomeStatement.eps,
                    netIncome = incomeStatement.netIncome
            )
        }
    }
}
