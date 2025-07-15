package io.dustin.finstage.financial.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ExternalSymbolListResponse(
        @JsonProperty("total")
        val total: Int,

        @JsonProperty("page")
        val page: Int,

        @JsonProperty("size")
        val size: Int,

        @JsonProperty("totalPages")
        val totalPages: Int,

        @JsonProperty("hasNext")
        val hasNext: Boolean,

        @JsonProperty("hasPrev")
        val hasPrev: Boolean,

        @JsonProperty("items")
        val items: List<ExternalSymbolItem>
)

data class ExternalSymbolItem(
        @JsonProperty("symbol")
        val symbol: String,

        @JsonProperty("name")
        val name: String,

        @JsonProperty("country")
        val country: String
)
