package io.dustin.finstage.financial.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class SymbolListRequest(

        @field:Min(1)
        val page: Int = 1,

        @field:Min(1)
        @field:Max(100)
        val size: Int = 20
) {
    companion object {
        fun of(page: Int, size: Int): SymbolListRequest {
            return SymbolListRequest(page, size)
        }
    }
}
