package io.dustin.finstage.symbol.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 심볼 검색 점수를 저장하는 JPA 엔티티
 */
@Entity
@Table(
        name = "symbol_search_score",
        uniqueConstraints = @UniqueConstraint(columnNames = {"symbol", "date"})
)
@Getter
@NoArgsConstructor
public class SymbolSearchScoreJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String symbol;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int score;

    public SymbolSearchScoreJpaEntity(String symbol, LocalDate date, int score) {
        this.symbol = symbol;
        this.date = date;
        this.score = score;
    }

    public void increase(int point) {
        this.score += point;
    }

    public void updateScore(int newScore) {
        this.score = newScore;
    }
}
