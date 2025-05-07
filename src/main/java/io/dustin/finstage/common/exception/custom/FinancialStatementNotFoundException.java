package io.dustin.finstage.common.exception.custom;

/**
 * 재무제표가 존재하지 않을 경우 발생
 */
public class FinancialStatementNotFoundException extends RuntimeException {
    public FinancialStatementNotFoundException(String symbol) {
        super("재무제표가 존재하지 않는 심볼입니다. 등록 요청이 필요합니다: " + symbol);
    }
}
