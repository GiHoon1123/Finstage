package io.dustin.finstage.symbol.adapter.out.persistence;


import io.dustin.finstage.symbol.adapter.out.persistence.SymbolSearchScoreJpaEntity;
import io.dustin.finstage.symbol.domain.SymbolSearchScore;

public class SymbolSearchScoreMapper {

    public static SymbolSearchScore toDomain(SymbolSearchScoreJpaEntity entity) {
        SymbolSearchScore domain = SymbolSearchScore.create(entity.getSymbol(), entity.getDate());
        domain.increase(entity.getScore());
        return domain;
    }

    public static SymbolSearchScoreJpaEntity toEntity(SymbolSearchScore domain) {
        return new SymbolSearchScoreJpaEntity(
                domain.getSymbol(),
                domain.getDate(),
                domain.getScore()
        );
    }
}
