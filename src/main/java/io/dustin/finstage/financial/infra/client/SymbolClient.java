package io.dustin.finstage.financial.infra.client;

import io.dustin.finstage.common.annotation.ExternalClient;
import io.dustin.finstage.common.http.HttpClient;
import io.dustin.finstage.financial.dto.ExternalSymbolListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@ExternalClient
@RequiredArgsConstructor
public class SymbolClient {

    private final HttpClient httpClient;

    public ExternalSymbolListResponse fetchSymbols(int page, int size) {
        String url = String.format("http://localhost:8081/symbols?page=%d&size=%d", page, size);
        return httpClient.get(url, ExternalSymbolListResponse.class);
    }
}
