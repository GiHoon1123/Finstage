package io.dustin.finstage.financial.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class ExternalIncomeStatementResponse(
        @JsonProperty("id")
        val id: Int,

        @JsonProperty("symbol")
        val symbol: String,

        @JsonProperty("date")
        val date: LocalDate,

        @JsonProperty("revenue")
        val revenue: Long,

        @JsonProperty("cost_of_revenue")
        val costOfRevenue: Long,

        @JsonProperty("gross_profit")
        val grossProfit: Long,

        @JsonProperty("operating_income")
        val operatingIncome: Long,

        @JsonProperty("net_income")
        val netIncome: Long,

        @JsonProperty("eps")
        val eps: Double
)
