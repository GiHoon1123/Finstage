package io.dustin.finstage.financial.application.port.in;

import io.dustin.finstage.financial.application.port.in.command.RequestFinancialStatementCommand;

public interface RequestFinancialStatementUseCase {
    void request(RequestFinancialStatementCommand command);
}



