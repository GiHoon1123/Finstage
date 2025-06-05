package io.dustin.finstage.content.service;

import io.dustin.finstage.common.annotation.UseCase;
import io.dustin.finstage.common.response.PagedResponse;
import io.dustin.finstage.content.domain.Content;
import io.dustin.finstage.content.dto.ContentListRequest;
import io.dustin.finstage.content.dto.ContentResponse;
import io.dustin.finstage.content.dto.ExternalContentListResponse;
import io.dustin.finstage.content.infra.ContentClient;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ContentService {

    private final ContentClient contentClient;


    public PagedResponse<ContentResponse> getAllContents(ContentListRequest request) {
        int page = request.getPage();
        int size = request.getSize();


        ExternalContentListResponse external = contentClient.fetchAllContents(page,size);

        List<ContentResponse> items = external.getItems().stream()
                .map(item -> Content.of(item.getId(), item.getSymbol(), item.getSummary(), item.getUrl(), item.getTitle(), item.getDate()))
                .map(ContentResponse::from)
                .toList();

        return PagedResponse.<ContentResponse>builder()
                .total(external.getTotal())
                .page(external.getPage())
                .size(external.getSize())
                .totalPages(external.getTotalPages())
                .hasNext(external.isHasNext())
                .hasPrev(external.isHasPrev())
                .items(items)
                .build();
    }

    public PagedResponse<ContentResponse> getContentsBySymbol(ContentListRequest request) {
        String symbol = request.getSymbol();
        int page = request.getPage();
        int size = request.getSize();


        ExternalContentListResponse external = contentClient.fetchContentsBySymbol(symbol,page,size);

        List<ContentResponse> items = external.getItems().stream()
                .map(item -> Content.of(item.getId(), item.getSymbol(), item.getSummary(), item.getUrl(), item.getTitle(), item.getDate()))
                .map(ContentResponse::from)
                .toList();

        return PagedResponse.<ContentResponse>builder()
                .total(external.getTotal())
                .page(external.getPage())
                .size(external.getSize())
                .totalPages(external.getTotalPages())
                .hasNext(external.isHasNext())
                .hasPrev(external.isHasPrev())
                .items(items)
                .build();
    }

}
