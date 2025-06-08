package io.dustin.finstage.common.util

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SlackNotifier(
        private val restTemplate: RestTemplate
) {

    @Value("\${slack.webhook.url}")
    private lateinit var webhookUrl: String

    private val log = LoggerFactory.getLogger(SlackNotifier::class.java)

    fun send(message: String) {
        try {
            val headers = HttpHeaders().apply {
                contentType = MediaType.APPLICATION_JSON
            }

            val payload = mapOf("text" to message)
            val request = HttpEntity(payload, headers)

            restTemplate.postForEntity(webhookUrl, request, String::class.java)
        } catch (e: Exception) {
            log.error("슬랙 알림 전송 실패", e)
        }
    }
}
