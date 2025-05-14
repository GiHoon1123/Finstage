package io.dustin.finstage.common.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PagedResponse<T> {

    private final int total;
    private final int page;
    private final int size;
    private final int totalPages;
    private final boolean hasNext;
    private final boolean hasPrev;
    private final List<T> items;
}
