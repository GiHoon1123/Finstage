package io.dustin.finstage.symbol.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * 심볼 검색 점수 저장용 JPA 리포지토리
 */
public interface SymbolSearchScoreRepository extends JpaRepository<SymbolSearchScoreJpaEntity, Long> {

    /**
     * 심볼과 날짜로 엔티티 조회 (유니크 제약)
     */
    Optional<SymbolSearchScoreJpaEntity> findBySymbol(String symbol);
}
