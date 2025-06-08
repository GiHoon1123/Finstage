package io.dustin.finstage.content.domain

import java.time.LocalDateTime

class Content private constructor(
        val id: Long,
        val symbol: String,
        val summary: String,
        val url: String,
        val title: String,
        val date: LocalDateTime
) {
    companion object {
        fun of(
                id: Long,
                symbol: String,
                summary: String,
                url: String,
                title: String,
                date: LocalDateTime
        ): Content {
            return Content(id, symbol, summary, url, title, date)
        }
    }
}
