package io.dustin.finstage.financial.service;

import io.dustin.finstage.common.annotation.UseCase;
import io.dustin.finstage.common.response.PagedResponse;
import io.dustin.finstage.financial.domain.Symbol;
import io.dustin.finstage.financial.dto.ExternalSymbolListResponse;
import io.dustin.finstage.financial.dto.SymbolListRequest;
import io.dustin.finstage.financial.dto.SymbolResponse;
import io.dustin.finstage.financial.infra.client.SymbolClient;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SymbolService {

    private final SymbolClient symbolClient;

    public PagedResponse<SymbolResponse> getSymbols(SymbolListRequest request) {
        int page = request.getPage();
        int size = request.getSize();

        ExternalSymbolListResponse external = symbolClient.fetchSymbols(page, size);

        List<SymbolResponse> items = external.getItems().stream()
                .map(item -> Symbol.of(item.getSymbol(), item.getName(), item.getCountry()))
                .map(SymbolResponse::from)
                .toList();

        return PagedResponse.<SymbolResponse>builder()
                .total(external.getTotal())
                .page(external.getPage())
                .size(external.getSize())
                .totalPages(external.getTotalPages())
                .hasNext(external.isHasNext())
                .hasPrev(external.isHasPrev())
                .items(items)
                .build();
    }
}
