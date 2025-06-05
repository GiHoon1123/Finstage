package io.dustin.finstage.financial.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ExternalSymbolListResponse {
    private int total;
    private int page;
    private int size;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrev;
    private List<ExternalSymbolItem> items;

    @Getter
    public static class ExternalSymbolItem {
        private String symbol;
        private String name;
        private String country;
    }
}

