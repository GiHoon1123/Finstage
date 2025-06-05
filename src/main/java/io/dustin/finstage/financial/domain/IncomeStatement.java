package io.dustin.finstage.financial.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class IncomeStatement {

    private final String symbol;
    private final LocalDate date;
    private final long revenue;
    private final long costOfRevenue;
    private final long grossProfit;
    private final long operatingIncome;
    private final long netIncome;
    private final double eps;

    private IncomeStatement(
            String symbol,
            LocalDate date,
            long revenue,
            long costOfRevenue,
            long grossProfit,
            long operatingIncome,
            long netIncome,
            double eps
    ) {
        this.symbol = symbol;
        this.date = date;
        this.revenue = revenue;
        this.costOfRevenue = costOfRevenue;
        this.grossProfit = grossProfit;
        this.operatingIncome = operatingIncome;
        this.netIncome = netIncome;
        this.eps = eps;
    }

    public static IncomeStatement of(
            String symbol,
            LocalDate date,
            long revenue,
            long costOfRevenue,
            long grossProfit,
            long operatingIncome,
            long netIncome,
            double eps
    ) {
        return new IncomeStatement(symbol, date, revenue, costOfRevenue, grossProfit, operatingIncome, netIncome, eps);
    }
}

