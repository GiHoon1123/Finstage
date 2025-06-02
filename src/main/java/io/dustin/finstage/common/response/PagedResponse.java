package io.dustin.finstage.common.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PagedResponse<T> {

    private final long total;       // 전체 항목 수
    private final int page;         // 현재 페이지
    private final int size;         // 페이지당 항목 수
    private final int totalPages;   // 전체 페이지 수
    private final boolean hasNext;  // 다음 페이지 존재 여부
    private final boolean hasPrev;  // 이전 페이지 존재 여부
    private final List<T> items;    // 실제 데이터 목록

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
