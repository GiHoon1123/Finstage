package io.dustin.finstage.common.infra.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 재무제표 등록 요청 메시지 DTO입니다.
 * - Java 서버에서 Python 서버로 재무제표 등록 요청 시 사용됩니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialRequestMessage {

    /**
     * 요청할 회사의 심볼
     */
    private String symbol;
}
