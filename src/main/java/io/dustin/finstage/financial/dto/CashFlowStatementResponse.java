package io.dustin.finstage.financial.dto;

import io.dustin.finstage.financial.domain.CashFlowStatement;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CashFlowStatementResponse {

    private final String symbol;
    private final LocalDate date;
    private final long operating_cash_flow;
    private final long investing_cash_flow;
    private final long financing_cash_flow;
    private final long free_cash_flow;

    public static CashFlowStatementResponse from(CashFlowStatement cashFlow) {
        return CashFlowStatementResponse.builder()
                .symbol(cashFlow.getSymbol())
                .date(cashFlow.getDate())
                .operating_cash_flow(cashFlow.getOperatingCashFlow())
                .investing_cash_flow(cashFlow.getInvestingCashFlow())
                .financing_cash_flow(cashFlow.getFinancingCashFlow())
                .free_cash_flow(cashFlow.getFreeCashFlow())
                .build();
    }
}
