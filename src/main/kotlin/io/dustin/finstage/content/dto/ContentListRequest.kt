package io.dustin.finstage.content.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

class ContentListRequest private constructor(
        var symbol: String?,

        @field:Min(1)
        var page: Int = 1,

        @field:Min(1)
        @field:Max(100)
        var size: Int = 20
) {
    companion object {
        fun of(symbol: String?, page: Int, size: Int): ContentListRequest {
            return ContentListRequest(symbol, page, size)
        }
    }
}
