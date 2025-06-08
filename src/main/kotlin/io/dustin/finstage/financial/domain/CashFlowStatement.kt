package io.dustin.finstage.financial.domain

import java.time.LocalDate

data class CashFlowStatement(
        val symbol: String,
        val date: LocalDate,
        val operatingCashFlow: Long,
        val investingCashFlow: Long,
        val financingCashFlow: Long,
        val freeCashFlow: Long
) {
    companion object {
        fun of(
                symbol: String,
                date: LocalDate,
                operatingCashFlow: Long,
                investingCashFlow: Long,
                financingCashFlow: Long,
                freeCashFlow: Long
        ): CashFlowStatement {
            return CashFlowStatement(
                    symbol,
                    date,
                    operatingCashFlow,
                    investingCashFlow,
                    financingCashFlow,
                    freeCashFlow
            )
        }
    }
}
