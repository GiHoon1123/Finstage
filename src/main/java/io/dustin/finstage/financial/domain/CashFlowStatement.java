package io.dustin.finstage.financial.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 현금흐름표 정보를 나타내는 도메인 모델
 * - symbol: 종목 티커
 * - operatingCashFlow: 영업활동현금흐름
 * - investingCashFlow: 투자활동현금흐름
 * - financingCashFlow: 재무활동현금흐름
 * - freeCashFlow: 잉여현금흐름
 * - date: 기준 일자
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CashFlowStatement {
    private final String symbol;
    private final BigDecimal operatingCashFlow;
    private final BigDecimal investingCashFlow;
    private final BigDecimal financingCashFlow;
    private final BigDecimal freeCashFlow;
    private final LocalDate date;

    public static CashFlowStatement of(
            String symbol,
            BigDecimal operatingCashFlow,
            BigDecimal investingCashFlow,
            BigDecimal financingCashFlow,
            BigDecimal freeCashFlow,
            LocalDate date
    ) {
        return new CashFlowStatement(symbol, operatingCashFlow, investingCashFlow, financingCashFlow, freeCashFlow, date);
    }
}

