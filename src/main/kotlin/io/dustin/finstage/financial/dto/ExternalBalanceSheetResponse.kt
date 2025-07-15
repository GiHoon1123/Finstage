package io.dustin.finstage.financial.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class ExternalBalanceSheetResponse(
        @JsonProperty("id")
        val id: Int,

        @JsonProperty("date")
        val date: LocalDate,

        @JsonProperty("total_liabilities")
        val totalLiabilities: Long,

        @JsonProperty("cash_and_equivalents")
        val cashAndEquivalents: Long?,

        @JsonProperty("symbol")
        val symbol: String,

        @JsonProperty("total_assets")
        val totalAssets: Long,

        @JsonProperty("total_equity")
        val totalEquity: Long,

        @JsonProperty("inventory")
        val inventory: Long?
)
