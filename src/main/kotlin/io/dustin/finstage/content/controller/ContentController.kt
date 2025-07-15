package io.dustin.finstage.content.controller

import io.dustin.finstage.common.response.CommonResponse
import io.dustin.finstage.common.response.PagedResponse
import io.dustin.finstage.content.dto.ContentListRequest
import io.dustin.finstage.content.dto.ContentResponse
import io.dustin.finstage.content.service.ContentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contents")
class ContentController(
        private val contentService: ContentService
) {

    @GetMapping
    @Operation(
            summary = "전체 콘텐츠 목록 조회",
            description = "모든 기업의 콘텐츠 목록을 페이지네이션하여 조회합니다.",
            tags = ["Content"]
    )
    fun getAllContents(
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @RequestParam(defaultValue = "1") page: Int,

            @Parameter(description = "페이지당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") size: Int
    ): CommonResponse<PagedResponse<ContentResponse>> {
        val request = ContentListRequest.of(null, page, size)
        return CommonResponse.success(contentService.getAllContents(request))
    }

    @GetMapping("/{symbol}")
    @Operation(
            summary = "특정 심볼의 콘텐츠 목록 조회",
            description = "특정 기업의 심볼(symbol)을 기반으로 해당 기업의 콘텐츠 목록을 조회합니다.",
            tags = ["Content"]
    )
    fun getContentsBySymbol(
            @Parameter(description = "기업 심볼", example = "AAPL")
            @PathVariable symbol: String,

            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @RequestParam(defaultValue = "1") page: Int,

            @Parameter(description = "페이지당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") size: Int
    ): CommonResponse<PagedResponse<ContentResponse>> {
        val request = ContentListRequest.of(symbol, page, size)
        return CommonResponse.success(contentService.getContentsBySymbol(request))
    }
}
