package io.dustin.finstage.financial.dto

import jakarta.validation.constraints.NotBlank

data class FinancialStatementRequest(

        @field:NotBlank(message = "symbol은 필수입니다.")
        val symbol: String
) {
    companion object {
        fun of(symbol: String): FinancialStatementRequest {
            return FinancialStatementRequest(symbol)
        }
    }
}
