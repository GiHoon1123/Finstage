package io.dustin.finstage.financial.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CashFlowStatement {
    private final String symbol;
    private final LocalDate date;
    private final long operatingCashFlow;
    private final long investingCashFlow;
    private final long financingCashFlow;
    private final long freeCashFlow;

    private CashFlowStatement(
            String symbol,
            LocalDate date,
            long operatingCashFlow,
            long investingCashFlow,
            long financingCashFlow,
            long freeCashFlow
    ) {
        this.symbol = symbol;
        this.date = date;
        this.operatingCashFlow = operatingCashFlow;
        this.investingCashFlow = investingCashFlow;
        this.financingCashFlow = financingCashFlow;
        this.freeCashFlow = freeCashFlow;
    }

    public static CashFlowStatement of(
            String symbol,
            LocalDate date,
            long operatingCashFlow,
            long investingCashFlow,
            long financingCashFlow,
            long freeCashFlow
    ) {
        return new CashFlowStatement(symbol, date, operatingCashFlow, investingCashFlow, financingCashFlow, freeCashFlow);
    }
}
