package io.dustin.finstage.financial.service

import io.dustin.finstage.common.annotation.UseCase
import io.dustin.finstage.common.response.PagedResponse
import io.dustin.finstage.financial.domain.Symbol
import io.dustin.finstage.financial.dto.SymbolListRequest
import io.dustin.finstage.financial.dto.SymbolResponse
import io.dustin.finstage.financial.infra.client.SymbolClient

@UseCase
class SymbolService(
        private val symbolClient: SymbolClient
) {

    fun getSymbols(request: SymbolListRequest): PagedResponse<SymbolResponse> {
        val page = request.page
        val size = request.size

        val external = symbolClient.fetchSymbols(page, size)

        val items = external?.items?.map {
            val symbol = Symbol.of(it.symbol, it.name, it.country)
            SymbolResponse.from(symbol)
        } ?: emptyList()

        return PagedResponse(
                total = external?.total?.toLong() ?: 0,
                page = external?.page ?: page,
                size = external?.size ?: size,
                totalPages = external?.totalPages ?: 0,
                hasNext = external?.hasNext ?: false,
                hasPrev = external?.hasPrev ?: false,
                items = items
        )
    }
}
