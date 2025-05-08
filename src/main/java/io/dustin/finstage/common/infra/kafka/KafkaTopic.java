package io.dustin.finstage.common.infra.kafka;

public final class KafkaTopic {

    /**
     * 회사 재무제표 등록 요청 토픽
     */
    public static final String FINANCIAL_STATEMENT_REQUEST = "financial.statement.request";

    /**
     * 회사 재무제표 등록 결과 수신 토픽
     */
    public static final String FINANCIAL_STATEMENT_RESULT = "financial.statement.result";

    private KafkaTopic() {
        // 인스턴스화 방지
    }
}
