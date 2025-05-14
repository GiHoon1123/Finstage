package io.dustin.finstage.financial.application.port.service;

import io.dustin.finstage.common.annotation.UseCase;
import io.dustin.finstage.financial.adapter.out.kafka.FinancialRequestProducer;
import io.dustin.finstage.financial.application.port.in.RequestFinancialStatementUseCase;
import io.dustin.finstage.financial.application.port.in.command.RequestFinancialStatementCommand;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RequestFinancialStatementService implements RequestFinancialStatementUseCase {

    private final FinancialRequestProducer financialRequestProducer;

    @Override
    public void request(RequestFinancialStatementCommand command) {
        financialRequestProducer.send(command.getSymbol());
    }
}

