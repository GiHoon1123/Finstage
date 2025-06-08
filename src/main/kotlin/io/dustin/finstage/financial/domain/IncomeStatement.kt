package io.dustin.finstage.financial.domain

import java.time.LocalDate

data class IncomeStatement(
        val symbol: String,
        val date: LocalDate,
        val revenue: Long,
        val costOfRevenue: Long,
        val grossProfit: Long,
        val operatingIncome: Long,
        val netIncome: Long,
        val eps: Double
) {
    companion object {
        fun of(
                symbol: String,
                date: LocalDate,
                revenue: Long,
                costOfRevenue: Long,
                grossProfit: Long,
                operatingIncome: Long,
                netIncome: Long,
                eps: Double
        ): IncomeStatement {
            return IncomeStatement(
                    symbol,
                    date,
                    revenue,
                    costOfRevenue,
                    grossProfit,
                    operatingIncome,
                    netIncome,
                    eps
            )
        }
    }
}
