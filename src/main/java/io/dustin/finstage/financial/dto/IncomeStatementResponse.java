package io.dustin.finstage.financial.dto;

import io.dustin.finstage.financial.domain.IncomeStatement;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class IncomeStatementResponse {

    private final String symbol;
    private final LocalDate date;
    private final long revenue;
    private final long cost_of_revenue;
    private final long gross_profit;
    private final long operating_income;
    private final double eps;
    private final long net_income;

    public static IncomeStatementResponse from(IncomeStatement incomeStatement) {
        return IncomeStatementResponse.builder()
                .symbol(incomeStatement.getSymbol())
                .date(incomeStatement.getDate())
                .revenue(incomeStatement.getRevenue())
                .cost_of_revenue(incomeStatement.getCostOfRevenue())
                .gross_profit(incomeStatement.getGrossProfit())
                .operating_income(incomeStatement.getOperatingIncome())
                .eps(incomeStatement.getEps())
                .net_income(incomeStatement.getNetIncome())
                .build();
    }
}
