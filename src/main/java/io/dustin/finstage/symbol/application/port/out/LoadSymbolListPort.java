package io.dustin.finstage.symbol.application.port.out;

import io.dustin.finstage.symbol.domain.Symbol;

import java.util.List;

public interface LoadSymbolListPort {

    List<Symbol> loadSymbols(int offset, int size);

    int countSymbols();
}
