package io.dustin.finstage.common.response

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
@Schema(description = "공통 응답 형식")
data class CommonResponse<T>(
        @Schema(description = "HTTP 상태 코드", example = "200")
        val status: Int,

        @Schema(description = "응답 메시지", example = "Success")
        val message: String,

        @Schema(description = "응답 데이터")
        val data: T? = null,

        @field:JsonIgnore
        @Schema(hidden = true)
        val errors: List<ValidationError>? = null
) {
    companion object {
        fun <T> success(data: T): CommonResponse<T> =
                CommonResponse(200, "Success", data)

        fun success(): CommonResponse<Void> =
                CommonResponse(200, "Success", null, null)

        fun <T> of(status: Int, message: String, data: T? = null): CommonResponse<T> =
                CommonResponse(status, message, data)

        fun <T> of(status: Int, message: String, data: T? = null, errors: List<ValidationError>? = null): CommonResponse<T> =
                CommonResponse(status, message, data, errors)
    }

    @Schema(description = "유효성 검증 에러")
    data class ValidationError(
            @Schema(description = "문제가 발생한 필드명", example = "email")
            val field: String,

            @Schema(description = "에러 메시지", example = "이메일 형식이 아닙니다.")
            val message: String
    )
}
