package io.dustin.finstage.financial.infra.client;

import io.dustin.finstage.common.annotation.ExternalClient;
import io.dustin.finstage.common.http.HttpClient;
import io.dustin.finstage.financial.dto.ExternalFinancialStatementResponse;
import io.dustin.finstage.financial.dto.ExternalSymbolListResponse;
import lombok.RequiredArgsConstructor;

@ExternalClient
@RequiredArgsConstructor
public class FinancialStatementClient {

    private final HttpClient httpClient;

    public ExternalFinancialStatementResponse fetchFinancialStatement(String symbol) {
        String url = String.format("http://localhost:8081/financials?symbol=%S", symbol );
        return httpClient.get(url, ExternalFinancialStatementResponse.class);
    }
}
