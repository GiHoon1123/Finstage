package io.dustin.finstage.symbol.application.port.in.query;

import lombok.Getter;

@Getter
public class GetSymbolsQuery {

    private final int page;
    private final int size;

    public GetSymbolsQuery(int page, int size) {
        if (page < 1) throw new IllegalArgumentException("page must be >= 1");
        if (size < 1 || size > 100) throw new IllegalArgumentException("size must be between 1 and 100");

        this.page = page;
        this.size = size;
    }
}
