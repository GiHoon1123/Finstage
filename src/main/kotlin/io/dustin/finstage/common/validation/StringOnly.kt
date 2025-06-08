package io.dustin.finstage.common.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [StringOnlyValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class StringOnly(
        val message: String = "문자(한글, 영문)와 공백만 입력 가능합니다.",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)
