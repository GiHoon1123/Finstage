package io.dustin.finstage.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "페이지네이션 응답 형식")
public class PagedResponse<T> {

    @Schema(description = "전체 항목 수", example = "105")
    private final long total;

    @Schema(description = "현재 페이지 (1부터 시작)", example = "1")
    private final int page;

    @Schema(description = "페이지당 항목 수", example = "20")
    private final int size;

    @Schema(description = "전체 페이지 수", example = "6")
    private final int totalPages;

    @Schema(description = "다음 페이지 존재 여부", example = "true")
    private final boolean hasNext;

    @Schema(description = "이전 페이지 존재 여부", example = "false")
    private final boolean hasPrev;

    @Schema(description = "현재 페이지에 포함된 데이터 목록")
    private final List<T> items;

    @Builder
    public PagedResponse(long total, int page, int size, int totalPages, boolean hasNext, boolean hasPrev, List<T> items) {
        this.total = total;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrev = hasPrev;
        this.items = items;
    }
}
