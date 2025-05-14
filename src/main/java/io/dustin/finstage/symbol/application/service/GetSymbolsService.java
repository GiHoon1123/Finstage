package io.dustin.finstage.symbol.application.service;

import io.dustin.finstage.common.annotation.UseCase;
import io.dustin.finstage.common.response.PagedResponse;
import io.dustin.finstage.symbol.application.port.in.query.GetSymbolsQuery;
import io.dustin.finstage.symbol.application.port.in.GetSymbolsUseCase;
import io.dustin.finstage.symbol.application.port.out.LoadSymbolListPort;
import io.dustin.finstage.symbol.domain.Symbol;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetSymbolsService implements GetSymbolsUseCase {

    private final LoadSymbolListPort loadSymbolListPort;

    @Override
    public PagedResponse<Symbol> getSymbols(GetSymbolsQuery query) {
        int page = query.getPage();
        int size = query.getSize();
        int offset = (page-1)  * size;

        List<Symbol> items = loadSymbolListPort.loadSymbols(offset, size);
        int total = loadSymbolListPort.countSymbols();
        int totalPages = (int) Math.ceil((double) total / size);

        return PagedResponse.<Symbol>builder()
                .total(total)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .hasNext(page < totalPages)
                .hasPrev(page > 1)
                .items(items)
                .build();
    }
}
