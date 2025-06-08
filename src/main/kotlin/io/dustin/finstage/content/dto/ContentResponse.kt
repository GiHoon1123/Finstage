package io.dustin.finstage.content.dto

import io.dustin.finstage.content.domain.Content
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "콘텐츠 응답 DTO")
data class ContentResponse(

        @Schema(description = "고유값", example = "1")
        val id: Long,

        @Schema(description = "기업 심볼", example = "AAPL")
        val symbol: String,

        @Schema(description = "콘텐츠 요약본", example = "Comprehensive up-to-date news coverage, aggregated from sources all over the world by Google News.")
        val summary: String,

        @Schema(description = "콘텐츠 웹 주소", example = "https://news.google.com/...")
        val url: String,

        @Schema(description = "콘텐츠 제목", example = "Google News")
        val title: String,

        @Schema(description = "콘텐츠 발행일", example = "2025-05-13T05:48:16")
        val date: LocalDateTime
) {
    companion object {
        fun from(content: Content): ContentResponse {
            return ContentResponse(
                    id = content.id,
                    symbol = content.symbol,
                    summary = content.summary,
                    url = content.url,
                    title = content.title,
                    date = content.date
            )
        }
    }
}
