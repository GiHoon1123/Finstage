package io.dustin.finstage.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CommonResponse<T> {

    @Schema(description = "HTTP 상태 코드", example = "200")
    private final int status;

    @Schema(description = "응답 메시지", example = "Success")
    private final String message;

    @Schema(description = "응답 데이터")
    private final T data;

    @Schema(description = "유효성 검증 에러 목록", hidden = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<ValidationError> errors;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "Success", data, null);
    }

    public static CommonResponse<Void> success() {
        return new CommonResponse<>(200, "Success", null, null);
    }

    public static <T> CommonResponse<T> of(int status, String message, T data) {
        return new CommonResponse<>(status, message, data, null);
    }

    public static <T> CommonResponse<T> of(int status, String message, T data, List<ValidationError> errors) {
        return new CommonResponse<>(status, message, data, errors);
    }

    public static <T> CommonResponse<T> of(int status, String message) {
        return new CommonResponse<>(status, message,null,null);
    }

    @Getter
    @RequiredArgsConstructor
    public static class ValidationError {
        @Schema(description = "문제가 발생한 필드명", example = "email")
        private final String field;

        @Schema(description = "에러 메시지", example = "이메일 형식이 아닙니다.")
        private final String message;
    }
}
