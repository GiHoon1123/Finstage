package io.dustin.finstage.symbol.application.port.out;


import io.dustin.finstage.symbol.domain.SymbolSearchScore;

import java.time.LocalDate;
import java.util.Optional;

public interface LoadSymbolSearchScorePort {

    /**
     * 주어진 symbol과 날짜 기준으로 기존 점수 기록을 조회합니다.
     * @param symbol 조회 대상 심볼
     * @param date 조회 기준 날짜
     * @return 점수 기록 (있으면 반환, 없으면 empty)
     */
    Optional<SymbolSearchScore> load(String symbol);
}