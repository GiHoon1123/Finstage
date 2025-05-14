package io.dustin.finstage.symbol.adapter.out.persistence;

import io.dustin.finstage.symbol.application.port.out.LoadSymbolSearchScorePort;
import io.dustin.finstage.symbol.application.port.out.SaveSymbolSearchScorePort;
import io.dustin.finstage.symbol.domain.SymbolSearchScore;
import io.dustin.finstage.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

import static io.dustin.finstage.symbol.adapter.out.persistence.SymbolSearchScoreMapper.*;


@PersistenceAdapter
@RequiredArgsConstructor
public class SymbolSearchScoreAdapter implements
        LoadSymbolSearchScorePort,
        SaveSymbolSearchScorePort {

    private final SymbolSearchScoreRepository repository;

    @Override
    public Optional<SymbolSearchScore> load(String symbol) {
        return repository.findBySymbol(symbol)
                .map(SymbolSearchScoreMapper::toDomain); // ✅ 정적 팩토리 + increase 사용
    }

    @Override
    public void save(SymbolSearchScore domain) {
        SymbolSearchScoreJpaEntity entity = repository
                .findBySymbol(domain.getSymbol())
                .map(found -> {
                    found.updateScore(domain.getScore()); // ✅ 점수 대입만
                    return found;
                })
                .orElse(toEntity(domain)); // 신규 저장

        repository.save(entity);
    }
}
