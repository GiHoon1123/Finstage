package io.dustin.finstage.financial.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExternalIncomeStatement {
    private long revenue;
    private String symbol;
    private int id;
    private long cost_of_revenue;
    private long gross_profit;
    private long operating_income;
    private double eps;
    private LocalDate date;
    private long net_income;
}
