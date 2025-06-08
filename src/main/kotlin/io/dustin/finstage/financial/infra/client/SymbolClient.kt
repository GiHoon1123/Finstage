package io.dustin.finstage.financial.infra.client

import io.dustin.finstage.common.annotation.ExternalClient
import io.dustin.finstage.common.http.HttpClient
import io.dustin.finstage.financial.dto.ExternalSymbolListResponse

@ExternalClient
class SymbolClient(
        private val httpClient: HttpClient
) {

    fun fetchSymbols(page: Int, size: Int): ExternalSymbolListResponse? {
        val url = "http://localhost:8081/symbols?page=$page&size=$size"
        return httpClient.get(url, ExternalSymbolListResponse::class.java)
    }
}
