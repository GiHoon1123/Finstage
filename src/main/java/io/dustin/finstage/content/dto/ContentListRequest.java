package io.dustin.finstage.content.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContentListRequest {

    private String symbol;

    @Min(1)
    private int page = 1;

    @Min(1)
    @Max(100)
    private int size = 20;

    public static ContentListRequest of(String symbol, int page, int size) {
        ContentListRequest request = new ContentListRequest();
        request.symbol = symbol;
        request.page = page;
        request.size = size;
        return request;
    }
}
