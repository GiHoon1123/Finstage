package io.dustin.finstage.symbol.application.port.out;

import io.dustin.finstage.symbol.domain.SymbolSearchScore;

public interface SaveSymbolSearchScorePort {

    /**
     * 심볼 검색 점수를 저장하거나 갱신합니다.
     * @param score 저장 대상 도메인 객체
     */
    void save(SymbolSearchScore score);
}
