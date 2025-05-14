package io.dustin.finstage.symbol.adapter.in.web;

import io.dustin.finstage.common.annotation.WebAdapter;
import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.common.response.PagedResponse;
import io.dustin.finstage.symbol.application.port.in.GetSymbolsUseCase;
import io.dustin.finstage.symbol.application.port.in.query.GetSymbolsQuery;
import io.dustin.finstage.symbol.domain.Symbol;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/symbols")
public class GetSymbolsController {

    private final GetSymbolsUseCase getSymbolsUseCase;

    @Operation(
            summary = "Get list of stock symbols (paginated)",
            description = "Retrieves a paginated list of registered stock symbols.\n" +
                    "This API allows clients to browse available stock tickers stored in the system.\n" +
                    "\n" +
                    "- Results are sorted alphabetically by `symbol`.\n" +
                    "- Use `page` and `size` query parameters to control pagination.\n" +
                    "- The response contains basic company info: `symbol`, `name`, and `country`.",
            parameters = {
                    @Parameter(name = "page", description = "Page number (starts from 1)", in = ParameterIn.QUERY, example = "1"),
                    @Parameter(name = "size", description = "Number of items per page (max 100)", in = ParameterIn.QUERY, example = "20")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of stock symbols",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    @GetMapping
    public ResponseEntity<CommonResponse<PagedResponse<Symbol>>> getSymbols(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        GetSymbolsQuery query = new GetSymbolsQuery(page, size);
        PagedResponse<Symbol> result = getSymbolsUseCase.getSymbols(query);
        return ResponseEntity.ok(CommonResponse.success(result));
    }
}
