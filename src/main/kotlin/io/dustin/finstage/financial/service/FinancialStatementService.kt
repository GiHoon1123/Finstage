package io.dustin.finstage.financial.service

import io.dustin.finstage.common.annotation.UseCase
import io.dustin.finstage.financial.domain.BalanceSheet
import io.dustin.finstage.financial.domain.CashFlowStatement
import io.dustin.finstage.financial.domain.IncomeStatement
import io.dustin.finstage.financial.dto.FinancialStatementRequest
import io.dustin.finstage.financial.dto.FinancialStatementResponse
import io.dustin.finstage.financial.infra.client.FinancialStatementClient

@UseCase
class FinancialStatementService(
        private val financialStatementClient: FinancialStatementClient
) {

    fun getFinancialStatements(request: FinancialStatementRequest): FinancialStatementResponse? {
        val symbol = request.symbol
        val external = financialStatementClient.fetchFinancialStatement(symbol) ?: return null

        val incomeStatements = external.incomeStatement.map {
            IncomeStatement.of(
                    it.symbol,
                    it.date,
                    it.revenue,
                    it.costOfRevenue,
                    it.grossProfit,
                    it.operatingIncome,
                    it.netIncome,
                    it.eps
            )
        }

        val balanceSheets = external.balanceSheet.map {
            BalanceSheet.of(
                    it.symbol,
                    it.date,
                    it.totalAssets,
                    it.totalLiabilities,
                    it.totalEquity,
                    it.cashAndEquivalents,
                    it.inventory
            )
        }

        val cashFlows = external.cashFlow.map {
            CashFlowStatement.of(
                    it.symbol,
                    it.date,
                    it.operatingCashFlow,
                    it.investingCashFlow,
                    it.financingCashFlow,
                    it.freeCashFlow
            )
        }

        return FinancialStatementResponse.of(incomeStatements, balanceSheets, cashFlows)
    }
}
