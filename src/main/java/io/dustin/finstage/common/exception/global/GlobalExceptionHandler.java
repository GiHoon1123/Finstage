package io.dustin.finstage.common.exception.global;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.dustin.finstage.common.exception.custom.AlreadyRegisteredCompanyException;
import io.dustin.finstage.common.exception.custom.DuplicateCompanyAccessException;
import io.dustin.finstage.common.exception.custom.FinancialStatementNotFoundException;
import io.dustin.finstage.common.exception.custom.InvalidDepartmentException;
import io.dustin.finstage.common.response.CommonResponse;
import io.dustin.finstage.common.util.SlackNotifier;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private  final SlackNotifier slackNotifier;

    @ExceptionHandler(FinancialStatementNotFoundException.class)
    public ResponseEntity<CommonResponse<Void>> handleFinancialStatementNotFoundException(FinancialStatementNotFoundException ex) {
        return ResponseEntity.badRequest().body(
                CommonResponse.of(400, ex.getMessage(), null)
        );
    }

    @ExceptionHandler(InvalidDepartmentException.class)
    public ResponseEntity<CommonResponse<Void>> handleInvalidDepartment(InvalidDepartmentException ex) {
        return ResponseEntity.badRequest().body(
                CommonResponse.of(400, ex.getMessage(), null)
        );
    }

    @ExceptionHandler(DuplicateCompanyAccessException.class)
    public ResponseEntity<CommonResponse<Void>> handleDuplicateCompanyAccessException(DuplicateCompanyAccessException ex) {
        return ResponseEntity.badRequest().body(
                CommonResponse.of(400, ex.getMessage(), null)
        );
    }

    @ExceptionHandler(AlreadyRegisteredCompanyException.class)
    public ResponseEntity<CommonResponse<Void>> handleAlreadyRegisteredCompanyException(AlreadyRegisteredCompanyException ex) {
        return ResponseEntity.badRequest().body(
                CommonResponse.of(400, ex.getMessage(), null)
        );
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse<Void>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        // 타입 변환 실패: ex) String → Long 매핑 실패
        if (cause instanceof InvalidFormatException invalidFormatException) {
            List<CommonResponse.ValidationError> errors = List.of(
                    new CommonResponse.ValidationError(
                            buildFieldPath(invalidFormatException.getPath()),
                            makeFriendlyTypeErrorMessage(invalidFormatException.getTargetType())
                    )
            );

            return ResponseEntity.badRequest().body(
                    CommonResponse.of(400, "요청 데이터 타입 오류입니다.", null, errors)
            );
        }

        // 나머지 (배열, 객체 문제 등): 그냥 퉁쳐서 안내
        return ResponseEntity.badRequest().body(
                CommonResponse.of(400, "요청 형식이 올바르지 않습니다. (타입 불일치 또는 잘못된 JSON)", null)
        );
    }

    /**
     * @Valid 실패: DTO 객체 검증 실패
     * - Controller 단에서 @Valid 붙은 객체 검증 실패할 때 발생
     * - 예: @NotEmpty, @Email 등의 조건을 만족하지 못할 때
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<CommonResponse.ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new CommonResponse.ValidationError(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(CommonResponse.of(400, "요청에 오류가 있습니다.", null, validationErrors));
    }


    /**
     * 단일 필드 제약 조건 위반: 개별 파라미터 @Valid 검증 실패
     * - @Validated 붙은 단일 파라미터(@RequestParam 등) 검증 실패 시
     * - 제약 조건 위배
     * - 예: @Min, @Max, @Size 등의 단일 필드 검증
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponse<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<CommonResponse.ValidationError> validationErrors = ex.getConstraintViolations()
                .stream()
                .map(violation -> new CommonResponse.ValidationError(
                        extractFieldName(violation.getPropertyPath().toString()),
                        violation.getMessage()))
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(CommonResponse.of(400, "요청 파라미터 검증 오류가 발생했습니다.", null, validationErrors));
    }

    /**
     * 그 외 모든 예상하지 못한 서버 에러 처리
     * - 예상하지 못한 예외가 발생할 경우
     * - 서버 내부 오류 (500)로 통일하여 응답
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception ex) {
        ex.printStackTrace(); // 서버 콘솔에 출력

        // 슬랙으로 에러 알림
        slackNotifier.send(buildSlackErrorMessage(ex));

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonResponse.of(500, "서버 내부 오류가 발생했습니다.", null));
    }

    private String extractFieldName(String propertyPath) {
        if (propertyPath == null) {
            return "";
        }
        String[] parts = propertyPath.split("\\.");
        return parts.length > 0 ? parts[parts.length - 1] : propertyPath;
    }

    /**
     * 예: companies[0].companyId
     */
    private String buildFieldPath(List<JsonMappingException.Reference> path) {
        if (path == null || path.isEmpty()) {
            return "unknown"; // 경로가 없으면
        }

        StringBuilder sb = new StringBuilder();
        for (JsonMappingException.Reference ref : path) {
            if (ref.getFieldName() != null) {
                if (sb.length() > 0) {
                    sb.append(".");
                }
                sb.append(ref.getFieldName());
            }
            if (ref.getIndex() >= 0) {
                sb.append("[").append(ref.getIndex()).append("]");
            }
        }
        return sb.toString();
    }


    // 기대 타입에 따라 사용자 친화적 에러 메시지 생성
    private String makeFriendlyTypeErrorMessage(Class<?> targetType) {
        if (targetType == Long.class || targetType == Integer.class) {
            return "숫자만 입력 가능합니다.";
        }
        if (targetType == String.class) {
            return "문자열 형식이어야 합니다.";
        }
        // 그 외 타입은 그냥 타입명으로
        return "올바른 타입이 아닙니다. 기대 타입: " + targetType.getSimpleName();
    }

    private String buildSlackErrorMessage(Exception ex) {
        return """
                🚨 *500 서버 에러 발생*
                *예외 타입:* %s
                *메시지:* %s
                """.formatted(ex.getClass().getSimpleName(), ex.getMessage() != null ? ex.getMessage() : "No message");
    }

}
