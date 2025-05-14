package io.dustin.finstage.symbol.application.service;

import io.dustin.finstage.symbol.application.port.in.TrackSymbolSearchUseCase;
import io.dustin.finstage.symbol.application.port.in.command.TrackSymbolSearchCommand;
import io.dustin.finstage.symbol.application.port.out.LoadSymbolSearchScorePort;
import io.dustin.finstage.symbol.application.port.out.SaveSymbolSearchScorePort;
import io.dustin.finstage.symbol.domain.SymbolSearchScore;
import io.dustin.finstage.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * 사용자의 심볼 검색 또는 행동을 추적하여 점수를 누적하는 유스케이스 구현체
 */
@UseCase
@RequiredArgsConstructor
public class TrackSymbolSearchService implements TrackSymbolSearchUseCase {

    private final LoadSymbolSearchScorePort loadPort;
    private final SaveSymbolSearchScorePort savePort;

    @Override
    public void track(TrackSymbolSearchCommand command) {
        String symbol = command.getSymbol();
        int score = command.getScore();
        LocalDate today = LocalDate.now();
        System.out.println("score  " + score);
        SymbolSearchScore symbolSearchScore = loadPort
                .load(symbol)
                .orElseGet(() -> SymbolSearchScore.create(symbol, today));

        System.out.println("current score "+ symbolSearchScore.getScore());
        symbolSearchScore.increase(score);
        System.out.println("after score "+ symbolSearchScore.getScore());

        savePort.save(symbolSearchScore);
    }
}

