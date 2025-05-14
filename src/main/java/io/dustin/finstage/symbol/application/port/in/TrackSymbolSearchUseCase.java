package io.dustin.finstage.symbol.application.port.in;

import io.dustin.finstage.symbol.application.port.in.command.TrackSymbolSearchCommand;

/**
 * 심볼 검색 추적 요청을 처리하는 유스케이스 인터페이스
 */
public interface TrackSymbolSearchUseCase {

    /**
     * 사용자의 심볼 검색/행위를 추적하고 점수를 누적합니다.
     *
     * @param command 검색 요청 (symbol, score 포함)
     */
    void track(TrackSymbolSearchCommand command);
}
