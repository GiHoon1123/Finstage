package io.dustin.finstage.financial.domain

import java.time.LocalDate

data class BalanceSheet(
        val symbol: String,
        val date: LocalDate,
        val totalAssets: Long,
        val totalLiabilities: Long,
        val totalEquity: Long,
        val cashAndEquivalents: Long?, // nullable
        val inventory: Long?           // nullable
) {
    companion object {
        fun of(
                symbol: String,
                date: LocalDate,
                totalAssets: Long,
                totalLiabilities: Long,
                totalEquity: Long,
                cashAndEquivalents: Long?,
                inventory: Long?
        ): BalanceSheet {
            return BalanceSheet(
                    symbol,
                    date,
                    totalAssets,
                    totalLiabilities,
                    totalEquity,
                    cashAndEquivalents,
                    inventory
            )
        }
    }
}
