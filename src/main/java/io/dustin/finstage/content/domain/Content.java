package io.dustin.finstage.content.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Content {

    private final Long id;
    private final String symbol;
    private final String summary;
    private final String url;
    private final String title;
    private final LocalDateTime date;



    public static Content of(Long id, String symbol, String summary, String url, String title, LocalDateTime date) {
        return new Content(id, symbol, summary, url, title, date);
    }

}

