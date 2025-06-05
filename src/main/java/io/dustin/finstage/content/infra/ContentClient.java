package io.dustin.finstage.content.infra;

import io.dustin.finstage.common.annotation.ExternalClient;
import io.dustin.finstage.common.http.HttpClient;
import io.dustin.finstage.content.dto.ExternalContentListResponse;
import lombok.RequiredArgsConstructor;

@ExternalClient
@RequiredArgsConstructor
public class ContentClient {

    private final HttpClient httpClient;


    public ExternalContentListResponse fetchAllContents(int page, int size){
        String url = String.format("http://localhost:8082/contents?page=%d&size=%d", page, size);
        return httpClient.get(url, ExternalContentListResponse.class);
    }

    public ExternalContentListResponse fetchContentsBySymbol(String symbol, int page, int size) {
        String url = String.format("http://localhost:8082/contents/%s?page=%d&size=%d", symbol, page, size);
        return httpClient.get(url, ExternalContentListResponse.class);
    }

}
