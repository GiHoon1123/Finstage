package io.dustin.finstage.symbol.domain;

import io.dustin.finstage.common.exception.custom.InvalidScoreException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolSearchScore {

    private final String symbol;
    private final LocalDate date;
    private int score;

    public static SymbolSearchScore create(String symbol, LocalDate date) {
        return new SymbolSearchScore(symbol, date, 0); // 초기 점수는 0
    }

    public void increase(int point) {
        if (point <= 0) {
            throw new InvalidScoreException("잘못된 점수입니다. 1 이상이어야 합니다.");
        }
        this.score += point;
    }
}

