package io.dustin.finstage.symbol.application.port.in.command;


import io.dustin.finstage.common.validation.StringOnly;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrackSymbolSearchCommand {

    @NotEmpty(message = "symbol은 필수 입력입니다.")
    @StringOnly
    @Schema(description = "심볼 티커", example = "AAPL")
    private String symbol;

    @Schema(description = "사용자 행동에 대한 점수 (예: 검색 1점, 클릭 3점)", example = "1")
    private int score;

    public TrackSymbolSearchCommand(String symbol, int score) {
        this.symbol = symbol;
        this.score = score;
    }
}

