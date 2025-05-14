package io.dustin.finstage.financial.application.port.service;

import io.dustin.finstage.common.annotation.UseCase;
import io.dustin.finstage.common.exception.custom.FinancialStatementNotFoundException;
import io.dustin.finstage.financial.application.port.in.GetFinancialStatementUseCase;
import io.dustin.finstage.financial.application.port.out.LoadFinancialStatementPort;
import io.dustin.finstage.financial.domain.FinancialStatement;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetFinancialStatementService implements GetFinancialStatementUseCase {

    private final LoadFinancialStatementPort loadFinancialStatementPort;

    @Override
    public FinancialStatement getBySymbol(String symbol) {
        return loadFinancialStatementPort.load(symbol)
                .orElseThrow(() -> new FinancialStatementNotFoundException("재무제표가 존재하지 않는 심볼입니다: " + symbol));
    }
}
