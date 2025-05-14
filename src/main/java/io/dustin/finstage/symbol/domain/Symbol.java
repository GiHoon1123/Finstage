package io.dustin.finstage.symbol.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Symbol {
    private final String symbol;
    private final String name;
    private final String country;

    public  static Symbol of(String symbol, String name, String country){
        return new Symbol(symbol,name,country);
    }
}
