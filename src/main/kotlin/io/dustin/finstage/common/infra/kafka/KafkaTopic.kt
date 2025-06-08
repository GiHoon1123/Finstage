package io.dustin.finstage.common.infra.kafka

/**
 * Kafka 토픽 이름 상수 정의
 */
object KafkaTopic {

    /** 회사 재무제표 등록 요청 토픽 */
    const val FINANCIAL_STATEMENT_REQUEST = "financial.statement.request"

    /** 회사 재무제표 등록 결과 수신 토픽 */
    const val FINANCIAL_STATEMENT_RESULT = "financial.statement.result"
}
