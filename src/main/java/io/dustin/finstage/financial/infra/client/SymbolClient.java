package io.dustin.finstage.financial.infra.client;

import io.dustin.finstage.financial.dto.ExternalSymbolListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SymbolClient {

    private final RestTemplate restTemplate;

    public ExternalSymbolListResponse fetchSymbols(int page, int size) {
        String url = String.format("http://localhost:8081/symbols?page=%d&size=%d", page, size);
        return restTemplate.getForObject(url, ExternalSymbolListResponse.class);
    }
}
