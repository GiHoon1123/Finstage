package io.dustin.salesmgmt.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringOnlyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringOnly {
    String message() default "문자(한글, 영문)와 공백만 입력 가능합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
