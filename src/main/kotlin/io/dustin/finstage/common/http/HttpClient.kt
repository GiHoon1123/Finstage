package io.dustin.finstage.common.http

interface HttpClient {
    fun <T> get(url: String, responseType: Class<T>): T?
}
