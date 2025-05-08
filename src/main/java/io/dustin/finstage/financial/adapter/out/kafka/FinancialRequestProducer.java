package io.dustin.finstage.financial.adapter.out.kafka;

import io.dustin.finstage.common.annotation.KafkaAdapter;
import io.dustin.finstage.common.infra.kafka.KafkaProducerTemplate;
import io.dustin.finstage.common.infra.kafka.KafkaTopic;
import io.dustin.finstage.common.infra.kafka.dto.FinancialRequestMessage;
import lombok.RequiredArgsConstructor;

@KafkaAdapter
@RequiredArgsConstructor
public class FinancialRequestProducer {

    private final KafkaProducerTemplate producerTemplate;

    public void send(String symbol) {
        FinancialRequestMessage message = new FinancialRequestMessage(symbol);
        producerTemplate.send(KafkaTopic.FINANCIAL_STATEMENT_REQUEST, message);
    }
}
