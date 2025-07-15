package io.dustin.finstage.financial.infra.client

import ExternalFinancialStatementResponse
import io.dustin.finstage.common.annotation.ExternalClient
import io.dustin.finstage.common.http.HttpClient

@ExternalClient
class FinancialStatementClient(
        private val httpClient: HttpClient
) {

    fun fetchFinancialStatement(symbol: String): ExternalFinancialStatementResponse? {
        val url = "http://localhost:8081/financials?symbol=${symbol}"
        return httpClient.get(url, ExternalFinancialStatementResponse::class.java)
    }
}
