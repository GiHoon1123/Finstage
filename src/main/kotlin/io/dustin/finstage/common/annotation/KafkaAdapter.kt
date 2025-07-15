package io.dustin.finstage.common.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class KafkaAdapter(
        @get:AliasFor(annotation = Component::class)
        val value: String = ""
)
