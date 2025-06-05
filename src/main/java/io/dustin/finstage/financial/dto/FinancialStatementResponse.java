package io.dustin.finstage.financial.dto;


import io.dustin.finstage.financial.domain.BalanceSheet;
import io.dustin.finstage.financial.domain.CashFlowStatement;
import io.dustin.finstage.financial.domain.IncomeStatement;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FinancialStatementResponse {

    private final List<IncomeStatementResponse> income_statement;
    private final List<BalanceSheetResponse> balance_sheet;
    private final List<CashFlowStatementResponse> cash_flow;

    public static FinancialStatementResponse of(
            List<IncomeStatement> incomeStatements,
            List<BalanceSheet> balanceSheets,
            List<CashFlowStatement> cashFlows
    ) {
        return FinancialStatementResponse.builder()
                .income_statement(incomeStatements.stream().map(IncomeStatementResponse::from).toList())
                .balance_sheet(balanceSheets.stream().map(BalanceSheetResponse::from).toList())
                .cash_flow(cashFlows.stream().map(CashFlowStatementResponse::from).toList())
                .build();
    }
}
