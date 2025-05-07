package io.dustin.finstage.financial.application.port.in;

import io.dustin.finstage.financial.domain.FinancialStatement;

public interface GetFinancialStatementUseCase {
    FinancialStatement getBySymbol(String symbol);
}
