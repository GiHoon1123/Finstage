package io.dustin.finstage.content.dto;

import io.dustin.finstage.content.domain.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContentResponse {

    @Schema(description = "고유값", example = "1")
    private final Long id;

    @Schema(description = "기업 심볼", example = "AAPL")
    private final String symbol;

    @Schema(description = "콘텐츠 요약본", example = "Comprehensive up-to-date news coverage, aggregated from sources all over the world by Google News.")
    private final String summary;

    @Schema(description = "콘텐츠 웹 주소", example = "https://news.google.com/rss/articles/CBMif0FVX3lxTE5RZnJSSDBCc1pIM2xZenVyVGdpMEc2dXFFZEc1U2RBbF9ZMUI2Zmo3TVRXLTNZc01NQ3otZU1LNGdrajhDZEJVSjQtdlMyMEVSOHd3RlBBRTVqaFNQUU1EMXBkR3lkSmxmX0g3T3V5NWRiUko0WjRleUtCLTFOX28?oc=5")
    private final String url;

    @Schema(description = "콘텐츠 제목", example = "Google News")
    private final String title;

    @Schema(description = "콘텐츠 발행일", example = "2025-05-13T05:48:16")
    private final LocalDateTime date;

    public static ContentResponse from(Content content) {
        return ContentResponse.builder()
                .id(content.getId())
                .symbol(content.getSymbol())
                .summary(content.getSummary())
                .url(content.getUrl())
                .title(content.getTitle())
                .date(content.getDate())
                .build();
    }

}
