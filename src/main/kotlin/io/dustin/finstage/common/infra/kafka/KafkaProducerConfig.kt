package io.dustin.finstage.common.infra.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
@EnableKafka
class KafkaProducerConfig(
        @Value("\${spring.kafka.bootstrap-servers}")
        private val bootstrapServers: String
) {

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> {
        val configProps = mapOf<String, Any>(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun financialRequestTopic(): NewTopic {
        return NewTopic(KafkaTopic.FINANCIAL_STATEMENT_REQUEST, 1, 1)
    }

    @Bean
    fun financialResultTopic(): NewTopic {
        return NewTopic(KafkaTopic.FINANCIAL_STATEMENT_RESULT, 1, 1)
    }
}
