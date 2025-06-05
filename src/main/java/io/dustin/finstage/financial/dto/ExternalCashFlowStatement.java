package io.dustin.finstage.financial.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExternalCashFlowStatement {
    private String symbol;
    private long investing_cash_flow;
    private long free_cash_flow;
    private LocalDate date;
    private long operating_cash_flow;
    private int id;
    private long financing_cash_flow;
}
