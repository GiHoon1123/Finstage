package io.dustin.finstage.financial.application.port.out;


import io.dustin.finstage.financial.domain.FinancialStatement;

import java.util.Optional;

/**
 * 외부(Python 서버)에서 종목(symbol)에 대한 재무제표 데이터를 조회하는 포트
 */
public interface LoadFinancialStatementPort {

    /**
     * 주어진 심볼에 해당하는 재무제표를 외부 시스템에서 조회합니다.
     *
     * @param symbol 종목 심볼 (예: GOOGL, AAPL 등)
     * @return 조회된 재무제표 (없을 경우 Optional.empty 반환)
     */
    Optional<FinancialStatement> load(String symbol);
}
