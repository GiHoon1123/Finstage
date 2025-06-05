package io.dustin.finstage.financial.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Symbol {
    private final String symbol;
    private final String name;
    private final String country;

    private Symbol(String symbol, String name, String country) {
        this.symbol = symbol;
        this.name = name;
        this.country = country;
    }

    public static Symbol of(String symbol, String name, String country) {
        return new Symbol(symbol, name, country);
    }
}