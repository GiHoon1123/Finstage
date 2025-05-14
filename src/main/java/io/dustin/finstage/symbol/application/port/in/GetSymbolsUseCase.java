package io.dustin.finstage.symbol.application.port.in;

import io.dustin.finstage.common.response.PagedResponse;
import io.dustin.finstage.symbol.application.port.in.query.GetSymbolsQuery;
import io.dustin.finstage.symbol.domain.Symbol;

public interface GetSymbolsUseCase {
    PagedResponse<Symbol> getSymbols(GetSymbolsQuery query);
}
