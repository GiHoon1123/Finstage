package io.dustin.finstage.symbol.adapter.in.web;

import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.symbol.application.port.in.TrackSymbolSearchUseCase;
import io.dustin.finstage.symbol.application.port.in.command.TrackSymbolSearchCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/symbols")
@RequiredArgsConstructor

public class TrackSymbolSearchController {

    private final TrackSymbolSearchUseCase trackSymbolSearchUseCase;

    @Operation(summary = "심볼 검색 추적", description = "사용자가 특정 심볼을 검색하거나 클릭하는 행위를 추적합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 점수를 누적했습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 매개변수로 인해 요청이 실패했습니다.")
    })
    @PostMapping("/track")
    public ResponseEntity<CommonResponse<Void>> trackSymbolSearch(
            @Valid @RequestBody TrackSymbolSearchCommand command
    ) {
        trackSymbolSearchUseCase.track(command);
        return ResponseEntity.ok(CommonResponse.success(null));
    }
}

