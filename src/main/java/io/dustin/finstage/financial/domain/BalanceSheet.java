package io.dustin.finstage.financial.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BalanceSheet {
    private final String symbol;
    private final LocalDate date;
    private final long totalAssets;
    private final long totalLiabilities;
    private final long totalEquity;
    private final Long cashAndEquivalents; // nullable
    private final Long inventory;          // nullable

    private BalanceSheet(
            String symbol,
            LocalDate date,
            long totalAssets,
            long totalLiabilities,
            long totalEquity,
            Long cashAndEquivalents,
            Long inventory
    ) {
        this.symbol = symbol;
        this.date = date;
        this.totalAssets = totalAssets;
        this.totalLiabilities = totalLiabilities;
        this.totalEquity = totalEquity;
        this.cashAndEquivalents = cashAndEquivalents;
        this.inventory = inventory;
    }

    public static BalanceSheet of(
            String symbol,
            LocalDate date,
            long totalAssets,
            long totalLiabilities,
            long totalEquity,
            Long cashAndEquivalents,
            Long inventory
    ) {
        return new BalanceSheet(symbol, date, totalAssets, totalLiabilities, totalEquity, cashAndEquivalents, inventory);
    }


}
