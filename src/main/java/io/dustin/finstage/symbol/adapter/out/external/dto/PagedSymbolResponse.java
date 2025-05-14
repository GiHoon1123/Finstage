package io.dustin.finstage.symbol.adapter.out.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PagedSymbolResponse {
    private int total;
    private int page;
    private int size;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("has_next")
    private boolean hasNext;

    @JsonProperty("has_prev")
    private boolean hasPrev;

    private List<SymbolResponse> items = Collections.emptyList();
}