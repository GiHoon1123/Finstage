package io.dustin.finstage.common.http;

public interface HttpClient {
    <T> T get(String url, Class<T> responseType);
}
