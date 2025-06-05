package io.dustin.finstage.financial.controller;

import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.common.response.PagedResponse;
import io.dustin.finstage.financial.dto.SymbolListRequest;
import io.dustin.finstage.financial.dto.SymbolResponse;
import io.dustin.finstage.financial.service.SymbolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/symbols")
public class SymbolController {

    private final SymbolService symbolService;

    @GetMapping
    @Operation(
            summary = "심볼 목록 조회",
            description = "외부 재무제표 서버로부터 심볼 리스트를 조회합니다. 쿼리 파라미터로 페이지네이션을 지정할 수 있습니다.",
            tags = { "Symbol" }
    )
    public CommonResponse<PagedResponse<SymbolResponse>> getSymbols(
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @RequestParam(defaultValue = "1") int page,

            @Parameter(description = "페이지당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") int size
    ) {
        SymbolListRequest request = SymbolListRequest.of(page, size);
        PagedResponse<SymbolResponse> pagedResponse = symbolService.getSymbols(request);
        return CommonResponse.success(pagedResponse);
    }

}
