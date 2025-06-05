package io.dustin.finstage.financial.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ExternalFinancialStatementResponse {
    private List<ExternalIncomeStatement> income_statement;
    private List<ExternalBalanceSheet> balance_sheet;
    private List<ExternalCashFlowStatement> cash_flow;
}
