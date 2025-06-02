package io.dustin.finstage.financial.infra.client;

import io.dustin.finstage.financial.dto.ExternalSymbolListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public interface SymbolClient {
    ExternalSymbolListResponse fetchSymbols(int page, int size);
}
