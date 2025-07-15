package io.dustin.finstage.common.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "페이지네이션 응답 형식")
data class PagedResponse<T>(
        @Schema(description = "전체 항목 수", example = "105")
        val total: Long,

        @Schema(description = "현재 페이지 (1부터 시작)", example = "1")
        val page: Int,

        @Schema(description = "페이지당 항목 수", example = "20")
        val size: Int,

        @Schema(description = "전체 페이지 수", example = "6")
        val totalPages: Int,

        @Schema(description = "다음 페이지 존재 여부", example = "true")
        val hasNext: Boolean,

        @Schema(description = "이전 페이지 존재 여부", example = "false")
        val hasPrev: Boolean,

        val items: List<T>
)
