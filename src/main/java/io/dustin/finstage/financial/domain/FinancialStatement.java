package io.dustin.finstage.financial.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 하나의 종목(symbol)에 대한 재무제표 묶음 도메인
 * - incomeStatements: 손익계산서 리스트
 * - balanceSheets: 재무상태표 리스트
 * - cashFlowStatements: 현금흐름표 리스트
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FinancialStatement {
    @JsonProperty("income_statement")
    private final List<IncomeStatement> incomeStatements;
    @JsonProperty("balance_sheet")
    private final List<BalanceSheet> balanceSheets;
    @JsonProperty("cash_flow")
    private final List<CashFlowStatement> cashFlowStatements;

    public static FinancialStatement of(
            List<IncomeStatement> incomeStatements,
            List<BalanceSheet> balanceSheets,
            List<CashFlowStatement> cashFlowStatements
    ) {
        return new FinancialStatement(incomeStatements, balanceSheets, cashFlowStatements);
    }
}
