package io.dustin.finstage.financial.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FinancialStatementRequest {

    @NotBlank(message = "symbol은 필수입니다.")
    private String symbol;

    public static FinancialStatementRequest of(String symbol) {
        FinancialStatementRequest request = new FinancialStatementRequest();
        request.symbol = symbol;
        return request;
    }
}
