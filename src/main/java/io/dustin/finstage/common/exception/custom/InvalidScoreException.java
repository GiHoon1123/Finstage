package io.dustin.finstage.common.exception.custom;

/**
 * 잘못된 점수가 전달되었을 때 발생하는 예외입니다.
 * 예: 점수가 0 이하일 경우
 */
public class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(String message) {
        super(message);
    }
}
