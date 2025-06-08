package io.dustin.finstage.financial.domain

data class FinancialStatement(
        val symbol: String,
        val incomeStatements: List<IncomeStatement>,
        val balanceSheets: List<BalanceSheet>,
        val cashFlowStatements: List<CashFlowStatement>
)
