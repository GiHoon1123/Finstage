package io.dustin.finstage.financial.dto;

import io.dustin.finstage.financial.domain.BalanceSheet;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BalanceSheetResponse {

    private final String symbol;
    private final LocalDate date;
    private final long total_liabilities;
    private final Long cash_and_equivalents; // null 가능
    private final long total_assets;
    private final long total_equity;
    private final Long inventory; // null 가능

    public static BalanceSheetResponse from(BalanceSheet balanceSheet) {
        return BalanceSheetResponse.builder()
                .symbol(balanceSheet.getSymbol())
                .date(balanceSheet.getDate())
                .total_liabilities(balanceSheet.getTotalLiabilities())
                .cash_and_equivalents(balanceSheet.getCashAndEquivalents())
                .total_assets(balanceSheet.getTotalAssets())
                .total_equity(balanceSheet.getTotalEquity())
                .inventory(balanceSheet.getInventory())
                .build();
    }
}
