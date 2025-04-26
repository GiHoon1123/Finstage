package io.dustin.salesmgmt.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CommonResponse<T> {

    private final int status;
    private final String message;
    private final T data;

    @JsonInclude(JsonInclude.Include.NON_NULL) // null이면 JSON 응답에서 아예 제외
    private final List<ValidationError> errors; // 에러 리스트 추가


    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "Success", data, null);
    }

    public static <T> CommonResponse<T> of(int status, String message, T data) {
        return new CommonResponse<>(status, message, data, null);
    }

    public static <T> CommonResponse<T> of(int status, String message, T data, List<ValidationError> errors) {
        return new CommonResponse<>(status, message, data, errors);
    }

    @Getter
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;
    }
}
