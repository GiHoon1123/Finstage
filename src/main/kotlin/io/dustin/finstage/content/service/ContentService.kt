package io.dustin.finstage.content.service

import io.dustin.finstage.common.annotation.UseCase
import io.dustin.finstage.common.response.PagedResponse
import io.dustin.finstage.content.domain.Content
import io.dustin.finstage.content.dto.ContentListRequest
import io.dustin.finstage.content.dto.ContentResponse
import io.dustin.finstage.content.infra.ContentClient

@UseCase
class ContentService(
        private val contentClient: ContentClient
) {

    fun getAllContents(request: ContentListRequest): PagedResponse<ContentResponse> {
        val page = request.page
        val size = request.size

        val external = contentClient.fetchAllContents(page, size)

        val items = external?.items?.mapNotNull {
            it.id?.let { id ->
                Content.of(id, it.symbol, it.summary, it.url, it.title, it.date)
            }?.let { content -> ContentResponse.from(content) }
        } ?: emptyList() // null일 경우 빈 리스트 반환

        return PagedResponse(
                total = external?.total?.toLong() ?: 0,
                page = external?.page ?: page,
                size = external?.size ?: size,
                totalPages = external?.totalPages ?: 0,
                hasNext = external?.hasNext ?: false,
                hasPrev = external?.hasPrev ?: false,
                items = items
        )
    }


    fun getContentsBySymbol(request: ContentListRequest): PagedResponse<ContentResponse> {
        val symbol = request.symbol
        val page = request.page
        val size = request.size

        val external = symbol?.let { contentClient.fetchContentsBySymbol(it, page, size) }

        val items = external?.items?.mapNotNull {
            it.id?.let { id -> Content.of(id, it.symbol, it.summary, it.url, it.title, it.date) }
                    ?.let { content -> ContentResponse.from(content) }
        } ?: emptyList()

        return PagedResponse(
                total = external?.total?.toLong() ?: 0,
                page = external?.page ?: page,
                size = external?.size ?: size,
                totalPages = external?.totalPages ?: 0,
                hasNext = external?.hasNext ?: false,
                hasPrev = external?.hasPrev ?: false,
                items = items
        )
    }
}

