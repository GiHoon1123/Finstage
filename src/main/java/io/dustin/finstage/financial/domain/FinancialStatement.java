package io.dustin.finstage.financial.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FinancialStatement {
    private final String symbol;
    private final List<IncomeStatement> incomeStatements;
    private final List<BalanceSheet> balanceSheets;
    private final List<CashFlowStatement> cashFlowStatements;
}
