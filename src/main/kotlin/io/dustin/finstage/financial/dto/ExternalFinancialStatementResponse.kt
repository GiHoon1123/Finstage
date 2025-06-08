import com.fasterxml.jackson.annotation.JsonProperty
import io.dustin.finstage.financial.dto.ExternalBalanceSheetResponse
import io.dustin.finstage.financial.dto.ExternalCashFlowStatementResponse
import io.dustin.finstage.financial.dto.ExternalIncomeStatementResponse

data class ExternalFinancialStatementResponse(
        @JsonProperty("income_statement")
        val incomeStatement: List<ExternalIncomeStatementResponse>,

        @JsonProperty("balance_sheet")
        val balanceSheet: List<ExternalBalanceSheetResponse>,

        @JsonProperty("cash_flow")
        val cashFlow: List<ExternalCashFlowStatementResponse>
)
