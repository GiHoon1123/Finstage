package io.dustin.finstage.financial.service;

import io.dustin.finstage.common.annotation.UseCase;
import io.dustin.finstage.financial.domain.BalanceSheet;
import io.dustin.finstage.financial.domain.CashFlowStatement;
import io.dustin.finstage.financial.domain.IncomeStatement;
import io.dustin.finstage.financial.dto.ExternalFinancialStatementResponse;
import io.dustin.finstage.financial.dto.FinancialStatementRequest;
import io.dustin.finstage.financial.dto.FinancialStatementResponse;
import io.dustin.finstage.financial.infra.client.FinancialStatementClient;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FinancialStatementService {

    private final FinancialStatementClient financialStatementClient;

    public FinancialStatementResponse getFinancialStatements(FinancialStatementRequest request) {
        String symbol = request.getSymbol();
        ExternalFinancialStatementResponse external = financialStatementClient.fetchFinancialStatement(symbol);

        List<IncomeStatement> incomeStatements = external.getIncome_statement().stream()
                .map(dto -> IncomeStatement.of(
                        dto.getSymbol(),
                        dto.getDate(),
                        dto.getRevenue(),
                        dto.getCost_of_revenue(),
                        dto.getGross_profit(),
                        dto.getOperating_income(),
                        dto.getNet_income(),
                        dto.getEps()
                ))
                .toList();

        List<BalanceSheet> balanceSheets = external.getBalance_sheet().stream()
                .map(dto -> BalanceSheet.of(
                        dto.getSymbol(),
                        dto.getDate(),
                        dto.getTotal_assets(),
                        dto.getTotal_liabilities(),
                        dto.getTotal_equity(),
                        dto.getCash_and_equivalents(),
                        dto.getInventory()
                ))
                .toList();

        List<CashFlowStatement> cashFlows = external.getCash_flow().stream()
                .map(dto -> CashFlowStatement.of(
                        dto.getSymbol(),
                        dto.getDate(),
                        dto.getOperating_cash_flow(),
                        dto.getInvesting_cash_flow(),
                        dto.getFinancing_cash_flow(),
                        dto.getFree_cash_flow()
                ))
                .toList();

        return FinancialStatementResponse.of(incomeStatements, balanceSheets, cashFlows);
    }
}

