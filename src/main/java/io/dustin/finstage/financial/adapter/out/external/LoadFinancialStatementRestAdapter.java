package io.dustin.finstage.financial.adapter.out.external;

import io.dustin.finstage.common.annotation.GraphApiAdapter;
import io.dustin.finstage.financial.application.port.out.LoadFinancialStatementPort;
import io.dustin.finstage.financial.domain.FinancialStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * 특정 서버로부터 재무제표를 조회하는 외부 어댑터
 */
@GraphApiAdapter
@RequiredArgsConstructor
public class LoadFinancialStatementRestAdapter implements LoadFinancialStatementPort {

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081/financials";

    @Override
    public Optional<FinancialStatement> load(String symbol) {
        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("symbol", symbol)
                .toUriString();

        System.out.println("📡 요청 URL: " + url);

        try {
            FinancialStatement response = restTemplate.getForObject(url, FinancialStatement.class);
            System.out.println("✅ 응답 수신: " + response);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            System.out.println("❌ 예외 발생: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}