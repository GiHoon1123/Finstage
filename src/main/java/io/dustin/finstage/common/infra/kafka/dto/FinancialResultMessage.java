package io.dustin.finstage.common.infra.kafka.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 파이썬 서버로부터 수신하는 재무제표 등록 처리 결과 메시지
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FinancialResultMessage {

    /**
     * 요청한 종목 심볼 (예: GOOGL)
     */
    private String symbol;

    /**
     * 처리 성공 여부
     */
    private boolean success;

    /**
     * 실패 사유 (성공 시 null)
     */
    private String reason;
}
