package io.dustin.salesmgmt.common.exception.global;

import io.dustin.salesmgmt.common.exception.custom.InvalidDepartmentException;
import io.dustin.salesmgmt.common.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDepartmentException.class)
    public ResponseEntity<CommonResponse<String>> handleInvalidDepartment(InvalidDepartmentException ex) {
        return ResponseEntity.badRequest().body(
                CommonResponse.of(400, ex.getMessage(), null)
        );
    }
}
