package io.dustin.finstage.financial.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.dustin.finstage.financial.domain.BalanceSheet
import io.dustin.finstage.financial.domain.CashFlowStatement
import io.dustin.finstage.financial.domain.IncomeStatement

data class FinancialStatementResponse(

        val incomeStatement: List<IncomeStatementResponse>,

        val balanceSheet: List<BalanceSheetResponse>,

        val cashFlow: List<CashFlowStatementResponse>
) {
    companion object {
        fun of(
                incomeStatements: List<IncomeStatement>,
                balanceSheets: List<BalanceSheet>,
                cashFlows: List<CashFlowStatement>
        ): FinancialStatementResponse {
            return FinancialStatementResponse(
                    incomeStatement = incomeStatements.map { IncomeStatementResponse.from(it) },
                    balanceSheet = balanceSheets.map { BalanceSheetResponse.from(it) },
                    cashFlow = cashFlows.map { CashFlowStatementResponse.from(it) }
            )
        }
    }
}
