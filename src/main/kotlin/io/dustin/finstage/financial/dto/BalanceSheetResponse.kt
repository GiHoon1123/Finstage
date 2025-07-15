package io.dustin.finstage.financial.dto

import io.dustin.finstage.financial.domain.BalanceSheet
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class BalanceSheetResponse(
        @Schema(description = "기업 심볼", example = "AAPL")
        val symbol: String,

        @Schema(description = "보고일자", example = "2024-09-28")
        val date: LocalDate,

        @Schema(description = "총 부채", example = "308030000000")
        val totalLiabilities: Long,

        @Schema(description = "현금 및 현금성 자산", example = "null") // 실제로 null인 경우가 많아 생략해도 됨
        val cashAndEquivalents: Long?,

        @Schema(description = "총 자산", example = "364980000000")
        val totalAssets: Long,

        @Schema(description = "자기자본", example = "56950000000")
        val totalEquity: Long,

        @Schema(description = "재고자산", example = "null")
        val inventory: Long?
) {
    companion object {
        fun from(balanceSheet: BalanceSheet): BalanceSheetResponse {
            return BalanceSheetResponse(
                    symbol = balanceSheet.symbol,
                    date = balanceSheet.date,
                    totalLiabilities = balanceSheet.totalLiabilities,
                    cashAndEquivalents = balanceSheet.cashAndEquivalents,
                    totalAssets = balanceSheet.totalAssets,
                    totalEquity = balanceSheet.totalEquity,
                    inventory = balanceSheet.inventory
            )
        }
    }
}
