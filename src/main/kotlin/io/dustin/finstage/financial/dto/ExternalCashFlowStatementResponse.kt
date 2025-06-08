package io.dustin.finstage.financial.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class ExternalCashFlowStatementResponse(
        @JsonProperty("id")
        val id: Int,

        @JsonProperty("symbol")
        val symbol: String,

        @JsonProperty("date")
        val date: LocalDate,

        @JsonProperty("operating_cash_flow")
        val operatingCashFlow: Long,

        @JsonProperty("investing_cash_flow")
        val investingCashFlow: Long,

        @JsonProperty("financing_cash_flow")
        val financingCashFlow: Long,

        @JsonProperty("free_cash_flow")
        val freeCashFlow: Long
)
