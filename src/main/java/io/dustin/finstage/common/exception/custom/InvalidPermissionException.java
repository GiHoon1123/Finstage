package io.dustin.finstage.common.exception.custom;

/**
 * 권한 목록에 중복이 존재하거나,
 * ADMIN 권한 이외에 다른 권한이 함께 포함된 경우 발생하는 예외
 */
public class InvalidPermissionException extends RuntimeException {
    public InvalidPermissionException(String message) {
        super(message);
    }
}

