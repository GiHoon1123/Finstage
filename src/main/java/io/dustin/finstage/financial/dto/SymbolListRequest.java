package io.dustin.finstage.financial.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SymbolListRequest {

    @Min(1)
    private int page = 1;

    @Min(1)
    @Max(100)
    private int size = 20;

    public static SymbolListRequest of(int page, int size) {
        SymbolListRequest request = new SymbolListRequest();
        request.page = page;
        request.size = size;
        return request;
    }
}

