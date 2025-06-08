package io.dustin.finstage.content.infra

import io.dustin.finstage.common.annotation.ExternalClient
import io.dustin.finstage.common.http.HttpClient
import io.dustin.finstage.content.dto.ExternalContentListResponse

@ExternalClient
class ContentClient(
        private val httpClient: HttpClient
) {

    fun fetchAllContents(page: Int, size: Int): ExternalContentListResponse? {
        val url = "http://localhost:8082/contents?page=$page&size=$size"
        return httpClient.get(url, ExternalContentListResponse::class.java)
    }

    fun fetchContentsBySymbol(symbol: String, page: Int, size: Int): ExternalContentListResponse? {
        val url = "http://localhost:8082/contents/$symbol?page=$page&size=$size"
        return httpClient.get(url, ExternalContentListResponse::class.java)
    }
}
