package io.dustin.finstage.symbol.adapter.out.external;

import io.dustin.finstage.symbol.adapter.out.external.dto.PagedSymbolResponse;
import io.dustin.finstage.symbol.adapter.out.external.dto.SymbolResponse;
import io.dustin.finstage.symbol.application.port.out.LoadSymbolListPort;
import io.dustin.finstage.symbol.domain.Symbol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SymbolDataRestAdapter implements LoadSymbolListPort {

    private final RestTemplate restTemplate;

    private static final String SYMBOL_API_BASE = "http://localhost:8081/symbols";

    @Override
    public List<Symbol> loadSymbols(int offset, int size) {
        int page = (offset / size) + 1;
        String url = UriComponentsBuilder.fromHttpUrl(SYMBOL_API_BASE)
                .queryParam("page", page)
                .queryParam("size", size)
                .toUriString();

        PagedSymbolResponse response = restTemplate.getForObject(url, PagedSymbolResponse.class);

        return response.getItems().stream()
                .map(dto -> Symbol.of(dto.getSymbol(), dto.getName(), dto.getCountry()))
                .collect(Collectors.toList());
    }

    @Override
    public int countSymbols() {
        String url = UriComponentsBuilder.fromHttpUrl(SYMBOL_API_BASE)
                .queryParam("page", 1)
                .queryParam("size", 1)
                .toUriString();

        PagedSymbolResponse response = restTemplate.getForObject(url, PagedSymbolResponse.class);
        return response.getTotal();
    }
}
