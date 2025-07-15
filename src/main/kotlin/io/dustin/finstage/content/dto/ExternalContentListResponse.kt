package io.dustin.finstage.content.dto

import java.time.LocalDateTime

data class ExternalContentListResponse(
        val total: Int,
        val page: Int,
        val size: Int,
        val totalPages: Int,
        val hasNext: Boolean,
        val hasPrev: Boolean,
        val items: List<ExternalContentItem>
) {
    data class ExternalContentItem(
            val id: Long?,
            val symbol: String,
            val summary: String,
            val url: String,
            val title: String,
            val date: LocalDateTime
    )
}
