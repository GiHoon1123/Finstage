package io.dustin.finstage.common.infra.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka 메시지 전송 템플릿 클래스
 *
 * - KafkaProducer를 래핑하여 공통 메시지 전송 기능 제공
 * - 토픽 이름과 메시지 객체를 받아 직렬화 후 Kafka로 전송
 * - Kafka 관련 설정은 KafkaProducerConfig에서 주입됨
 */
@Component
@RequiredArgsConstructor
public class KafkaProducerTemplate {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerTemplate.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 지정된 Kafka 토픽에 메시지를 전송
     *
     * @param topic Kafka 토픽 이름
     * @param message 전송할 메시지 객체
     */
    public void send(String topic, Object message) {
        kafkaTemplate.send(topic, message);
        log.info("📤 Kafka 메시지 전송 완료: topic={}, message={}", topic, message);
    }
}
