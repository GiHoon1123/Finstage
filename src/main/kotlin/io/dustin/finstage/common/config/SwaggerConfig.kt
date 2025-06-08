package io.dustin.finstage.common.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    companion object {
        private const val SECURITY_SCHEME_NAME = "bearerAuth"
    }

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
                .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(
                        Components().addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .info(
                        Info()
                                .title("Finstage API")
                                .description("Finstage JWT 인증 Swagger")
                                .version("1.0.0")
                )
    }
}
