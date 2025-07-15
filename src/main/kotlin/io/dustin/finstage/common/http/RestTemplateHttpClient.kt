package io.dustin.finstage.common.http

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RestTemplateHttpClient(
        private val restTemplate: RestTemplate
) : HttpClient {

    override fun <T> get(url: String, responseType: Class<T>): T? {
        return restTemplate.getForObject(url, responseType)
    }
}
