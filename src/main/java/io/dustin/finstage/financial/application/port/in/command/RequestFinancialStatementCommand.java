package io.dustin.finstage.financial.application.port.in.command;

import io.dustin.finstage.common.validation.StringOnly;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestFinancialStatementCommand {

    @NotEmpty(message = "symbol은 필수 입력입니다.")
    @StringOnly
    String symbol;

    public RequestFinancialStatementCommand(String symbol){
        this.symbol = symbol;
    }
}
