package io.dustin.finstage.content.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ExternalContentListResponse {
    private int total;
    private int page;
    private int size;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrev;
    private List<ExternalContentItem> items;


    @Getter
    public static class ExternalContentItem {
        private Long id;
        private String symbol;
        private String summary;
        private String url;
        private String title;
        private LocalDateTime date;

    }
}
