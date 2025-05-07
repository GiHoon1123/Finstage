package io.dustin.finstage.financial.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 손익계산서 정보를 나타내는 도메인 모델
 * - symbol: 종목 티커
 * - revenue: 총 수익
 * - costOfRevenue: 매출원가
 * - grossProfit: 총이익
 * - operatingIncome: 영업이익
 * - netIncome: 순이익
 * - eps: 주당순이익
 * - date: 기준 일자
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IncomeStatement {
    private final String symbol;
    private final BigDecimal revenue;
    private final BigDecimal costOfRevenue;
    private final BigDecimal grossProfit;
    private final BigDecimal operatingIncome;
    private final BigDecimal netIncome;
    private final BigDecimal eps;
    private final LocalDate date;

    public static IncomeStatement of(
            String symbol,
            BigDecimal revenue,
            BigDecimal costOfRevenue,
            BigDecimal grossProfit,
            BigDecimal operatingIncome,
            BigDecimal netIncome,
            BigDecimal eps,
            LocalDate date
    ) {
        return new IncomeStatement(symbol, revenue, costOfRevenue, grossProfit, operatingIncome, netIncome, eps, date);
    }
}
