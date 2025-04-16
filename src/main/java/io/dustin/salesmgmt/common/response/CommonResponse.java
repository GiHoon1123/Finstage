package io.dustin.salesmgmt.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonResponse<T> {

    private final int status;
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "Success", data);
    }

    public static <T> CommonResponse<T> of(int status, String message, T data) {
        return new CommonResponse<>(status, message, data);
    }
}
