package io.dustin.finstage.financial.adapter.in.kafka;
import io.dustin.finstage.common.annotation.WebAdapter;
import io.dustin.finstage.common.infra.kafka.dto.FinancialResultMessage;
import io.dustin.finstage.common.infra.kafka.KafkaTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Kafka로부터 재무제표 등록 결과 메시지를 수신하는 Consumer
 */
@WebAdapter
@Component
@Slf4j
public class FinancialResultConsumer {

    @KafkaListener(
            topics = KafkaTopic.FINANCIAL_STATEMENT_RESULT,
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "financialResultListenerContainerFactory"
    )
    public void consume(@Payload FinancialResultMessage message, ConsumerRecord<String, FinancialResultMessage> record) {
        log.info("📥 Kafka 결과 수신: topic={}, partition={}, offset={}, message={}",
                record.topic(),
                record.partition(),
                record.offset(),
                message
        );

        // 👉 필요한 후처리 로직 여기에 구현
        if (message.isSuccess()) {
            log.info("✅ [{}] 재무제표 등록 성공", message.getSymbol());
        } else {
            log.warn("❌ [{}] 재무제표 등록 실패 - 사유: {}", message.getSymbol(), message.getReason());
        }
    }
}

