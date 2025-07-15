package io.dustin.finstage.financial.dto

import io.dustin.finstage.financial.domain.Symbol
import io.swagger.v3.oas.annotations.media.Schema

data class SymbolResponse(
        @Schema(description = "심볼 티커", example = "AACB")
        val symbol: String,

        @Schema(description = "기업 이름", example = "Artius II Acquisition Inc. Class A Ordinary Shares")
        val name: String,

        @Schema(description = "국가", example = "United States")
        val country: String
) {
    companion object {
        fun from(symbol: Symbol): SymbolResponse {
            return SymbolResponse(
                    symbol = symbol.symbol,
                    name = symbol.name,
                    country = symbol.country
            )
        }
    }
}
