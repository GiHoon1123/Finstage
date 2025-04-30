package io.dustin.finstage.common.exception.custom;

import lombok.Getter;

@Getter
public class InvalidRequestBodyFormatException extends RuntimeException {
    private final String field;
    private final String expectedType;
    private final String rejectedValue;

    public InvalidRequestBodyFormatException(String field, String expectedType, String rejectedValue) {
        super("잘못된 타입 입력: 필드 '" + field + "'는 " + expectedType + " 타입이어야 합니다. (입력값: " + rejectedValue + ")");
        this.field = field;
        this.expectedType = expectedType;
        this.rejectedValue = rejectedValue;
    }
}
