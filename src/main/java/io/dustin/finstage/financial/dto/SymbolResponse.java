package io.dustin.finstage.financial.dto;

import io.dustin.finstage.financial.domain.Symbol;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SymbolResponse {
    @Schema(description = "심볼 티커", example = "AACB")
    private final String symbol;

    @Schema(description = "기업 이름", example = "Artius II Acquisition Inc. Class A Ordinary Shares")
    private final String name;

    @Schema(description = "국가", example = "United States")
    private final String country;

    public static SymbolResponse from(Symbol symbol) {
        return SymbolResponse.builder()
                .symbol(symbol.getSymbol())
                .name(symbol.getName())
                .country(symbol.getCountry())
                .build();
    }
}

