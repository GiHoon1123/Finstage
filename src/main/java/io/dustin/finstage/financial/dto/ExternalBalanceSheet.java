package io.dustin.finstage.financial.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExternalBalanceSheet {
    private int id;
    private LocalDate date;
    private long total_liabilities;
    private Long cash_and_equivalents;
    private String symbol;
    private long total_assets;
    private long total_equity;
    private Long inventory;
}

