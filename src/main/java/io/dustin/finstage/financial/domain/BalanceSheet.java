package io.dustin.finstage.financial.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 재무상태표 정보를 나타내는 도메인 모델
 * - symbol: 종목 티커
 * - totalAssets: 총자산
 * - totalLiabilities: 총부채
 * - totalEquity: 총자본
 * - cashAndEquivalents: 현금 및 현금성 자산 (nullable)
 * - inventory: 재고자산 (nullable)
 * - date: 기준 일자
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BalanceSheet {
    private final String symbol;
    private final BigDecimal totalAssets;
    private final BigDecimal totalLiabilities;
    private final BigDecimal totalEquity;
    private final BigDecimal cashAndEquivalents;
    private final BigDecimal inventory;
    private final LocalDate date;

    public static BalanceSheet of(
            String symbol,
            BigDecimal totalAssets,
            BigDecimal totalLiabilities,
            BigDecimal totalEquity,
            BigDecimal cashAndEquivalents,
            BigDecimal inventory,
            LocalDate date
    ) {
        return new BalanceSheet(symbol, totalAssets, totalLiabilities, totalEquity, cashAndEquivalents, inventory, date);
    }
}
