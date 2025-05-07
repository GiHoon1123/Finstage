package io.dustin.finstage.financial.application.port.in.query;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 재무제표 조회를 위한 쿼리 객체
 * - symbol: 조회할 종목 티커
 */
@Getter
public class GetFinancialStatementQuery {

    @NotNull(message = "심볼명은 필수 입니다.")
    private final String symbol;

    public GetFinancialStatementQuery(String symbol) {
        this.symbol = symbol;
    }
}

